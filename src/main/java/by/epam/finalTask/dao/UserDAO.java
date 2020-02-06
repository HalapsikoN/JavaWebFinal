package by.epam.finalTask.dao;

import by.epam.finalTask.entity.*;
import by.epam.finalTask.entity.util.Role;

import java.util.List;

public interface UserDAO {

    boolean addUser(User user, String password) throws DAOException;
    boolean addTrackToUser(int userId, int trackId) throws DAOException;
    boolean addAlbumToUser(int userId, int albumId) throws DAOException;
    boolean addPlaylistToUser(int userId, int playlistId) throws DAOException;

    User getUserById(int id) throws DAOException;
    User getUserByLogin(String login) throws DAOException;
    String getUserPasswordByLogin(String login) throws DAOException;

    boolean updateUserWalletById(int id, double wallet) throws DAOException;
    boolean updateUserPasswordById(int id, String password) throws DAOException;
    boolean updateUserNameById(int id, String name) throws DAOException;
    boolean updateUserRoleById(int id, Role role) throws DAOException;

    boolean deleteUserById(int id) throws DAOException;

    List<Track> getUserTracksById(int id) throws DAOException;
    List<Album> getUserAlbumsById(int id) throws DAOException;
    List<Playlist> getUserPlayListsById(int id) throws DAOException;
    List<Bonus> getUserBonusesById(int id) throws DAOException;
    List<User> getAllUsers() throws DAOException;
}
