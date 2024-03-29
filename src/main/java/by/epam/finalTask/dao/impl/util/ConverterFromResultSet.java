package by.epam.finalTask.dao.impl.util;

import by.epam.finalTask.dao.DAOException;
import by.epam.finalTask.dao.DAOFactory;
import by.epam.finalTask.dao.impl.util.auxiliary.*;
import by.epam.finalTask.entity.*;
import by.epam.finalTask.entity.util.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ConverterFromResultSet {

    private static final Logger logger = LogManager.getLogger(ConverterFromResultSet.class);

    private static final ConverterFromResultSet instance = new ConverterFromResultSet();

    private ConverterFromResultSet() {
    }

    public static ConverterFromResultSet getInstance() {
        return instance;
    }

    public User getUserFromResultSet(ResultSet resultSet) throws DAOException {
        User user = null;


        try {
            int id = resultSet.getInt(UserFields.ID.name());
            String name = resultSet.getString(UserFields.NAME.name());
            String login = resultSet.getString(UserFields.LOGIN.name());
            Role role = Role.valueOf(resultSet.getString(UserFields.ROLE.name()).toUpperCase());
            double wallet = resultSet.getDouble(UserFields.WALLET.name());

            user = new User(id, name, login, role, wallet);
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return user;
    }

    public Track getTrackFromResultSet(ResultSet resultSet) throws DAOException {
        Track track = null;

        try {
            int id = resultSet.getInt(TrackFields.ID.name());
            String name = resultSet.getString(TrackFields.NAME.name());
            String artist = resultSet.getString(TrackFields.ARTIST.name());
            java.sql.Date dateSql = resultSet.getDate(TrackFields.DATE.name());
            Calendar date = new GregorianCalendar();
            date.setTimeInMillis(dateSql.getTime());
            double price = resultSet.getDouble(TrackFields.PRICE.name());
            String filename = resultSet.getString(TrackFields.FILENAME.name());

            track = new Track(id, name, artist, date, price, filename);

        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return track;
    }

    public Album getEmptyAlbumFromResultSet(ResultSet resultSet) throws DAOException {
        Album album = null;

        try {
            int id = resultSet.getInt(AlbumFields.ID.name());
            String name = resultSet.getString(AlbumFields.NAME.name());
            String artist = resultSet.getString(AlbumFields.ARTIST.name());
            java.sql.Date dateSql = resultSet.getDate(AlbumFields.DATE.name());
            Calendar date = new GregorianCalendar();
            date.setTimeInMillis(dateSql.getTime());
            List<Track> trackList = new ArrayList<>();

            album = new Album(id, name, artist, date, trackList);

        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return album;
    }

    public Playlist getEmptyPlaylistFromResultSet(ResultSet resultSet) throws DAOException {
        Playlist playlist = null;

        try {
            int id = resultSet.getInt(PlaylistFields.ID.name());
            String name = resultSet.getString(PlaylistFields.NAME.name());
            java.sql.Date dateSql = resultSet.getDate(PlaylistFields.DATE.name());
            Calendar date = new GregorianCalendar();
            date.setTimeInMillis(dateSql.getTime());
            List<Track> trackList = new ArrayList<>();

            playlist = new Playlist(id, name, date, trackList);

        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return playlist;
    }

    public Bonus getBonusFromResultSet(ResultSet resultSet) throws DAOException {
        Bonus bonus = null;

        try {
            int id = resultSet.getInt(BonusFields.ID.name());
            String name = resultSet.getString(BonusFields.NAME.name());
            int discount = resultSet.getInt(BonusFields.DISCOUNT.name());
            java.sql.Date dateSql = resultSet.getDate(BonusFields.START_DATE.name());
            Calendar startDate = new GregorianCalendar();
            startDate.setTimeInMillis(dateSql.getTime());
            dateSql = resultSet.getDate(BonusFields.END_DATE.name());
            Calendar endDate = new GregorianCalendar();
            endDate.setTimeInMillis(dateSql.getTime());
            int userId = resultSet.getInt(BonusFields.USER_ID.name());

            bonus = new Bonus(id, name, discount, startDate, endDate, userId);

        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return bonus;
    }

    public Comment getCommentFromResultSet(ResultSet resultSet) throws DAOException {
        Comment comment = null;

        try {
            int id = resultSet.getInt(CommentFields.ID.name());
            int userId = resultSet.getInt(CommentFields.USER_ID.name());
            java.sql.Timestamp time = resultSet.getTimestamp(CommentFields.DATE.name());
            Calendar date = new GregorianCalendar();
            date.setTimeInMillis(time.getTime());
            int trackId = resultSet.getInt(CommentFields.TRACK_ID.name());
            String text = resultSet.getString(CommentFields.TEXT.name());
            String username = DAOFactory.getInstance().getSqlUserDAO().getUserById(userId).getName();

            comment = new Comment(id, userId, username, date, trackId, text);

        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return comment;
    }

    public Credit getCreditFromResultSet(ResultSet resultSet) throws DAOException {
        Credit credit = null;

        try {
            int id = resultSet.getInt(CommentFields.ID.name());
            int userId = resultSet.getInt(CreditFields.USER_ID.name());
            java.sql.Date sqlDate = resultSet.getDate(CreditFields.DATE_END.name());
            Calendar date = new GregorianCalendar();
            date.setTimeInMillis(sqlDate.getTime());
            double amount = resultSet.getDouble(CreditFields.CREDIT.name());

            credit = new Credit(id, amount, date, userId);

        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return credit;
    }
}
