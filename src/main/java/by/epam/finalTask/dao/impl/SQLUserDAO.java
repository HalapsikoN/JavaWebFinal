package by.epam.finalTask.dao.impl;

import by.epam.finalTask.dao.UserDAO;
import by.epam.finalTask.dao.impl.util.auxiliary.UserFields;
import by.epam.finalTask.dao.impl.util.ConverterFromResultSet;
import by.epam.finalTask.entity.*;
import by.epam.finalTask.entity.util.Role;
import by.epam.finalTask.dao.pool.ConnectionPoolException;
import by.epam.finalTask.dao.DAOException;
import by.epam.finalTask.dao.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLUserDAO implements UserDAO {

    private static final Logger logger = LogManager.getLogger(SQLUserDAO.class);

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final ConverterFromResultSet converterFromResultSet = ConverterFromResultSet.getInstance();

    private String sqlAddUser = "INSERT INTO users (name, login, password, role) values (?,?,?,?)";
    private String sqlGetUserById = "SELECT * FROM users WHERE id=?";
    private String sqlGetUserByLogin = "SELECT * FROM users WHERE login=?";
    private String sqlGetUserPasswordByLogin = "SELECT password FROM users WHERE login=?";
    private String sqlUpdateUserWalletById = "UPDATE users SET wallet=? where id=?";
    private String sqlUpdateUserPasswordById = "UPDATE users SET password=? where id=?";
    private String sqlUpdateUserNameById = "UPDATE users SET name=? where id=?";
    private String sqlUpdateUserRoleById = "UPDATE users SET role=? where id=?";
    private String sqlDeleteUserById = "DELETE FROM users where id=?";
    private String sqlGetUserTracksById = "SELECT tracks.* FROM users INNER JOIN us_tr ON users.id=us_tr.user_id INNER JOIN tracks ON us_tr.track_id=tracks.id WHERE users.id=?";
    private String sqlGetUserAlbumsById = "SELECT albums.* FROM users INNER JOIN us_al ON users.id=us_al.user_id INNER JOIN albums ON us_al.album_id=albums.id WHERE users.id=?";
    private String sqlGetUserPlaylistsById = "SELECT playlists.* FROM users INNER JOIN us_pl ON users.id=us_pl.user_id INNER JOIN playlists ON us_pl.playlist_id=playlists.id WHERE users.id=?";

    private Map<String, PreparedStatement> preparedStatementMap;

    public SQLUserDAO() {
        preparedStatementMap = new HashMap<>();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            logger.error(e);
        }
        prepareStatement(connection, sqlAddUser);
        prepareStatement(connection, sqlGetUserById);
        prepareStatement(connection, sqlGetUserByLogin);
        prepareStatement(connection, sqlGetUserPasswordByLogin);
        prepareStatement(connection, sqlUpdateUserWalletById);
        prepareStatement(connection, sqlUpdateUserPasswordById);
        prepareStatement(connection, sqlUpdateUserNameById);
        prepareStatement(connection, sqlUpdateUserRoleById);
        prepareStatement(connection, sqlDeleteUserById);
        prepareStatement(connection, sqlGetUserTracksById);
        prepareStatement(connection, sqlGetUserAlbumsById);
        prepareStatement(connection, sqlGetUserPlaylistsById);

        if(connection!=null) {
            connectionPool.closeConnection(connection);
        }
    }

    private void prepareStatement(Connection connection, String sql) {
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatementMap.put(sql, preparedStatement);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    @Override
    public boolean addUser(User user, String password) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlAddUser);

            if (preparedStatement != null) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getLogin());
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, user.getRole().name());

                resultRow = preparedStatement.executeUpdate();
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        if (resultRow == 0) {
            result = false;
        }
        return result;
    }

    @Override
    public User getUserById(int id) throws DAOException {
        ResultSet resultSet = null;
        User user = null;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetUserById);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    user = converterFromResultSet.getUserFromResultSet(resultSet);
                }
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return user;
    }

    @Override
    public User getUserByLogin(String login) throws DAOException {
        ResultSet resultSet = null;
        User user = null;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetUserByLogin);

            if (preparedStatement != null) {
                preparedStatement.setString(1, login);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    user = converterFromResultSet.getUserFromResultSet(resultSet);
                }
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return user;
    }

    @Override
    public String getUserPasswordByLogin(String login) throws DAOException {
        ResultSet resultSet = null;
        String result = null;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetUserPasswordByLogin);

            if (preparedStatement != null) {
                preparedStatement.setString(1, login);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    result = resultSet.getString(UserFields.PASSWORD.name());
                }
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return result;
    }

    @Override
    public boolean updateUserWalletById(int id, double wallet) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlUpdateUserWalletById);

            if (preparedStatement != null) {
                preparedStatement.setDouble(1, wallet);
                preparedStatement.setInt(2, id);

                resultRow = preparedStatement.executeUpdate();
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        if (resultRow == 0) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean updateUserPasswordById(int id, String password) throws DAOException {
        return updateString(id, password, sqlUpdateUserPasswordById);
    }

    @Override
    public boolean updateUserNameById(int id, String name) throws DAOException {
        return updateString(id, name, sqlUpdateUserNameById);
    }

    private boolean updateString(int id, String name, String sqlUpdateUserNameById) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlUpdateUserNameById);

            if (preparedStatement != null) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, id);

                resultRow = preparedStatement.executeUpdate();
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        if (resultRow == 0) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean updateUserRoleById(int id, Role role) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlUpdateUserRoleById);

            if (preparedStatement != null) {
                preparedStatement.setString(1, role.name());
                preparedStatement.setInt(2, id);

                resultRow = preparedStatement.executeUpdate();
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        if (resultRow == 0) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean deleteUserById(int id) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlDeleteUserById);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, id);

                resultRow = preparedStatement.executeUpdate();
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        if (resultRow == 0) {
            result = false;
        }
        return result;
    }

    @Override
    public List<Track> getUserTracksById(int id) throws DAOException {
        List<Track> result = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetUserTracksById);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, id);

                resultSet = preparedStatement.executeQuery();
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
            while (resultSet.next()) {
                Track track = converterFromResultSet.getTrackFromResultSet(resultSet);
                result.add(track);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return result;
    }

    @Override
    public List<Album> getUserAlbumsById(int id) throws DAOException {
        List<Album> result=new ArrayList<>();
        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetUserAlbumsById);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, id);

                resultSet = preparedStatement.executeQuery();
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
            while (resultSet.next()) {
                Album album = converterFromResultSet.getEmptyAlbumFromResultSet(resultSet);
                result.add(album);
            }
        }catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return result;
    }

    @Override
    public List<Playlist> getUserPlayListsById(int id) throws DAOException {
        List<Playlist> result=new ArrayList<>();
        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetUserPlaylistsById);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, id);

                resultSet = preparedStatement.executeQuery();
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
            while (resultSet.next()) {
                Playlist album = converterFromResultSet.getEmptyPlaylistFromResultSet(resultSet);
                result.add(album);
            }
        }catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return result;
    }

    @Override
    public List<Bonus> getUserBonusesById(int id) throws DAOException {
        return null;
    }
}

