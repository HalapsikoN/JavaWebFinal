package by.epam.finalTask.dao.impl;

import by.epam.finalTask.dao.PlaylistDAO;
import by.epam.finalTask.dao.impl.util.ConverterFromResultSet;
import by.epam.finalTask.entity.Playlist;
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

public class SQLPlaylistDAO implements PlaylistDAO {

    private static final Logger logger= LogManager.getLogger(SQLUserDAO.class);

    private static final ConnectionPool connectionPool=ConnectionPool.getInstance();
    private static final ConverterFromResultSet converterFromResultSet= ConverterFromResultSet.getInstance();

    private String sqlAddPlaylist ="INSERT INTO playlists (name, date) values (?,?)";
    private String sqlAddTrackToPlaylist ="INSERT INTO tr_pl (track_id, playlist_id) values (?,?)";
    private String sqlGetPlaylistById ="SELECT * FROM playlists WHERE id=?";
    private String sqlGetAllPlaylistTracks="SELECT tracks.* FROM playlists INNER JOIN tr_pl ON playlists.id=tr_pl.playlist_id INNER JOIN tracks ON tr_pl.playlist_id=tracks.id WHERE playlists.id=?";
    private String sqlUpdatePlaylistById ="UPDATE playlists SET name=?, date=? where id=?";
    private String sqlDeletePlaylistById ="DELETE FROM playlists where id=?";
    private String sqlGetAllPlaylists ="SELECT * FROM playlists";

    private Map<String, PreparedStatement> preparedStatementMap;

    public SQLPlaylistDAO() {
        preparedStatementMap= new HashMap<>();
        Connection connection= null;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            logger.error(e);
        }
        prepareStatement(connection, sqlAddPlaylist);
        prepareStatement(connection, sqlAddTrackToPlaylist);
        prepareStatement(connection, sqlGetPlaylistById);
        prepareStatement(connection, sqlGetAllPlaylistTracks);
        prepareStatement(connection, sqlUpdatePlaylistById);
        prepareStatement(connection, sqlDeletePlaylistById);
        prepareStatement(connection, sqlGetAllPlaylists);

        if(connection!=null) {
            connectionPool.closeConnection(connection);
        }
    }

    private void prepareStatement(Connection connection, String sql){
        if(connection!=null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatementMap.put(sql, preparedStatement);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    @Override
    public boolean addPlaylistWithOutTracks(Playlist playlist) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlAddPlaylist);

            if (preparedStatement != null) {
            preparedStatement.setString(1, playlist.getName());
            java.sql.Date date=new Date(playlist.getDate().getTimeInMillis());
            preparedStatement.setDate(2, date);

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
    public boolean addTrackToPlaylist(Playlist playlist, Track track) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlAddTrackToPlaylist);

            if (preparedStatement != null) {
            preparedStatement.setInt(1, track.getId());
            preparedStatement.setInt(2, playlist.getId());

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
    public boolean addTrackToPlaylistById(int playlist_id, int track_id) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlAddTrackToPlaylist);

            if (preparedStatement != null) {
            preparedStatement.setInt(1, track_id);
            preparedStatement.setInt(2, playlist_id);

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
    public Playlist getPlaylistById(int id) throws DAOException {
        ResultSet resultSet=null;
        Playlist playlist=null;

        try{
            PreparedStatement preparedStatement=preparedStatementMap.get(sqlGetPlaylistById);

            if (preparedStatement != null) {
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                playlist = converterFromResultSet.getEmptyPlaylistFromResultSet(resultSet);

                List<Track> trackList=playlist.getTrackList();
                preparedStatement=preparedStatementMap.get(sqlGetAllPlaylistTracks);
                preparedStatement.setInt(1,id);
                resultSet =preparedStatement.executeQuery();
                while (resultSet.next()){
                    Track track=converterFromResultSet.getTrackFromResultSet(resultSet);
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

        return playlist;
    }

    @Override
    public boolean updatePlaylistById(int id, Playlist playlist) throws DAOException {
        boolean result=true;
        int resultRow;

        try {
            PreparedStatement preparedStatement=preparedStatementMap.get(sqlUpdatePlaylistById);

            if (preparedStatement != null) {
            preparedStatement.setString(1, playlist.getName());
            java.sql.Date date=new Date(playlist.getDate().getTimeInMillis());
            preparedStatement.setDate(2, date);
            preparedStatement.setInt(3, id);

            resultRow=preparedStatement.executeUpdate();
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        if(resultRow==0){
            result=false;
        }
        return result;
    }

    @Override
    public boolean deletePlaylistBId(int id) throws DAOException {
        boolean result=true;
        int resultRow;

        try {
            PreparedStatement preparedStatement=preparedStatementMap.get(sqlDeletePlaylistById);

            if (preparedStatement != null) {
            preparedStatement.setInt(1, id);

            resultRow=preparedStatement.executeUpdate();
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        if(resultRow==0){
            result=false;
        }
        return result;
    }

    @Override
    public List<Playlist> getAllPlaylists() throws DAOException {
        ResultSet resultSet=null;
        List<Playlist> playlistList=new ArrayList<>();

        try{
            PreparedStatement preparedStatement=preparedStatementMap.get(sqlGetAllPlaylists);

            if (preparedStatement != null) {

                resultSet=preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Playlist playlist=converterFromResultSet.getEmptyPlaylistFromResultSet(resultSet);
                    if(playlist!=null) {
                        playlistList.add(playlist);
                    }

                }
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return playlistList;
    }
}
