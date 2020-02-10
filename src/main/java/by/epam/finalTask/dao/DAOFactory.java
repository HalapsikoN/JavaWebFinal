package by.epam.finalTask.dao;

import by.epam.finalTask.dao.impl.*;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO sqlUserDAO = new SQLUserDAO();
    private final TrackDAO sqlTrackDAO = new SQLTrackDAO();
    private final AlbumDAO sqlAlbumDAO = new SQLAlbumDAO();
    private final PlaylistDAO sqlPlaylistDAO = new SQLPlaylistDAO();
    private final CommentDAO sqlCommentDAO = new SQLCommentDAO();
    private final BonusDAO sqlBonusDAO = new SQLBonusDAO();
    private final CreditDAO sqlCreditDAO = new SQLCreditDAO();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getSqlUserDAO() {
        return sqlUserDAO;
    }

    public TrackDAO getSqlTrackDAO() {
        return sqlTrackDAO;
    }

    public AlbumDAO getSqlAlbumDAO() {
        return sqlAlbumDAO;
    }

    public PlaylistDAO getSqlPlaylistDAO() {
        return sqlPlaylistDAO;
    }

    public CommentDAO getSqlCommentDAO() {
        return sqlCommentDAO;
    }

    public BonusDAO getSqlBonusDAO() {
        return sqlBonusDAO;
    }

    public CreditDAO getSqlCreditDAO() {
        return sqlCreditDAO;
    }
}
