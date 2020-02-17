package by.epam.finalTask.dao.impl;

import by.epam.finalTask.dao.DAOException;
import by.epam.finalTask.dao.TrackDAO;
import by.epam.finalTask.dao.impl.util.ConverterFromResultSet;
import by.epam.finalTask.dao.pool.ConnectionPool;
import by.epam.finalTask.dao.pool.ConnectionPoolException;
import by.epam.finalTask.entity.Track;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLTrackDAO implements TrackDAO {

    private static final Logger logger = LogManager.getLogger(SQLUserDAO.class);

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final ConverterFromResultSet converterFromResultSet = ConverterFromResultSet.getInstance();

    private String sqlAddTrack = "INSERT INTO tracks (name, artist, date, price, filename) values (?,?,?,?,?)";
    private String sqlGetTrackById = "SELECT * FROM tracks WHERE id=?";
    private String sqlGetTrackByName = "SELECT * FROM tracks WHERE name=?";
    private String sqlUpdateTrackById = "UPDATE tracks SET name=?, artist=?, date=?, price=? where id=?";
    private String sqlDeleteTrackById = "DELETE FROM tracks where id=?";
    private String sqlGetAllTracks = "SELECT * FROM tracks";
    private String sqlGetAllTracksWithArtist = "SELECT * FROM tracks WHERE artist=?";
    private String sqlIsAlreadyHaveFilename = "SELECT * FROM tracks WHERE filename=?";

    private Map<String, PreparedStatement> preparedStatementMap;

    public SQLTrackDAO() {
        preparedStatementMap = new HashMap<>();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            logger.error(e);
        }
        prepareStatement(connection, sqlAddTrack);
        prepareStatement(connection, sqlGetTrackById);
        prepareStatement(connection, sqlGetTrackByName);
        prepareStatement(connection, sqlUpdateTrackById);
        prepareStatement(connection, sqlDeleteTrackById);
        prepareStatement(connection, sqlGetAllTracks);
        prepareStatement(connection, sqlGetAllTracksWithArtist);
        prepareStatement(connection, sqlIsAlreadyHaveFilename);

        if (connection != null) {
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
    public boolean addTrack(Track track) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlAddTrack);

            if (preparedStatement != null) {
                preparedStatement.setString(1, track.getName());
                preparedStatement.setString(2, track.getArtist());
                java.sql.Date date = new Date(track.getDate().getTimeInMillis());
                preparedStatement.setDate(3, date);
                preparedStatement.setDouble(4, track.getPrice());
                preparedStatement.setString(5, track.getFilename());

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
    public Track getTrackById(int id) throws DAOException {
        ResultSet resultSet = null;
        Track track = null;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetTrackById);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    track = converterFromResultSet.getTrackFromResultSet(resultSet);
                }
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return track;
    }

    @Override
    public Track getTrackByName(String name) throws DAOException {
        ResultSet resultSet = null;
        Track track = null;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetTrackByName);

            if (preparedStatement != null) {
                preparedStatement.setString(1, name);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    track = converterFromResultSet.getTrackFromResultSet(resultSet);
                }
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return track;
    }

    @Override
    public boolean updateTrackById(int id, Track track) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlUpdateTrackById);

            if (preparedStatement != null) {
                preparedStatement.setString(1, track.getName());
                preparedStatement.setString(2, track.getArtist());
                java.sql.Date date = new Date(track.getDate().getTimeInMillis());
                preparedStatement.setDate(3, date);
                preparedStatement.setDouble(4, track.getPrice());
                preparedStatement.setInt(5, id);

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
    public boolean deleteTrackById(int id) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlDeleteTrackById);

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
    public List<Track> getAllTracks() throws DAOException {
        List<Track> result = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetAllTracks);

            if (preparedStatement != null) {
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
    public List<Track> getTracksWithArtist(String artist) throws DAOException {
        List<Track> result = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetAllTracksWithArtist);

            if (preparedStatement != null) {
                preparedStatement.setString(1, artist);

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
    public boolean isAlreadyHaveFilename(String filename) throws DAOException {
        boolean result = true;
        ResultSet resultSet;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlIsAlreadyHaveFilename);

            if (preparedStatement != null) {
                preparedStatement.setString(1, filename);

                resultSet = preparedStatement.executeQuery();
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }

            if (!resultSet.next()) {
                result = false;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return result;
    }
}