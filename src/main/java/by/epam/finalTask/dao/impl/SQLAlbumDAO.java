package by.epam.finalTask.dao.impl;

import by.epam.finalTask.dao.AlbumDAO;
import by.epam.finalTask.dao.impl.util.ConverterFromResultSet;
import by.epam.finalTask.entity.Album;
import by.epam.finalTask.entity.Track;
import by.epam.finalTask.dao.pool.ConnectionPoolException;
import by.epam.finalTask.dao.DAOException;
import by.epam.finalTask.dao.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLAlbumDAO implements AlbumDAO {

    private static final Logger logger = LogManager.getLogger(SQLUserDAO.class);

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final ConverterFromResultSet converterFromResultSet = ConverterFromResultSet.getInstance();

    private String sqlAddAlbum = "INSERT INTO albums (name, artist, date) values (?,?,?)";
    private String sqlAddTrackToAlbum = "INSERT INTO tr_al (track_id, album_id) values (?,?)";
    private String sqlGetAlbumById = "SELECT * FROM albums WHERE id=?";
    private String sqlGetAllAlbumTracks = "SELECT tracks.* FROM albums INNER JOIN tr_al ON albums.id=tr_al.album_id INNER JOIN tracks ON tr_al.album_id=tracks.id WHERE albums.id=?";
    private String sqlUpdateAlbumById = "UPDATE albums SET name=?, artist=?, date=? where id=?";
    private String sqlDeleteAlbumById = "DELETE FROM albums where id=?";
    private String sqlGetAllAlbums = "SELECT * FROM albums";

    private Map<String, PreparedStatement> preparedStatementMap;

    public SQLAlbumDAO() {
        preparedStatementMap = new HashMap<>();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            logger.error(e);
        }
        prepareStatement(connection, sqlAddAlbum);
        prepareStatement(connection, sqlAddTrackToAlbum);
        prepareStatement(connection, sqlGetAlbumById);
        prepareStatement(connection, sqlGetAllAlbumTracks);
        prepareStatement(connection, sqlUpdateAlbumById);
        prepareStatement(connection, sqlDeleteAlbumById);
        prepareStatement(connection, sqlGetAllAlbums);

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
    public boolean addAlbumWithOutTracks(Album album) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlAddAlbum);

            if (preparedStatement != null) {
                preparedStatement.setString(1, album.getName());
                preparedStatement.setString(2, album.getArtist());
                java.sql.Date date = new Date(album.getDate().getTimeInMillis());
                preparedStatement.setDate(3, date);

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
    public boolean addTrackToAlbum(Album album, Track track) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlAddTrackToAlbum);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, track.getId());
                preparedStatement.setInt(2, album.getId());

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
    public boolean addTrackToAlbumById(int album_id, int track_id) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlAddTrackToAlbum);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, track_id);
                preparedStatement.setInt(2, album_id);

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
    public Album getAlbumById(int id) throws DAOException {
        ResultSet resultSet = null;
        Album album = null;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetAlbumById);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    album = converterFromResultSet.getEmptyAlbumFromResultSet(resultSet);

                    List<Track> trackList = album.getTrackList();
                    preparedStatement = preparedStatementMap.get(sqlGetAllAlbumTracks);
                    preparedStatement.setInt(1, id);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Track track = converterFromResultSet.getTrackFromResultSet(resultSet);
                        trackList.add(track);
                    }
                }
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return album;
    }

    @Override
    public boolean updateAlbumById(int id, Album album) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlUpdateAlbumById);

            if (preparedStatement != null) {
                preparedStatement.setString(1, album.getName());
                preparedStatement.setString(2, album.getArtist());
                java.sql.Date date = new Date(album.getDate().getTimeInMillis());
                preparedStatement.setDate(3, date);
                preparedStatement.setInt(4, id);

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
    public boolean deleteAlbumBId(int id) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlDeleteAlbumById);

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
    public List<Album> getAllAlbums() throws DAOException {
        ResultSet resultSet = null;
        List<Album> albumList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetAllAlbums);

            if (preparedStatement != null) {

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Album album = converterFromResultSet.getEmptyAlbumFromResultSet(resultSet);
                    if (album != null) {
                        albumList.add(album);
                    }
                }
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return albumList;
    }
}
