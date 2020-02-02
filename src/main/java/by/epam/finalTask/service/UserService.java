package by.epam.finalTask.service;

import by.epam.finalTask.entity.*;
import by.epam.finalTask.entity.util.Role;

import java.util.List;

public interface UserService {

    User signIn(String login, String password) throws ServiceException;

    boolean register(User user, String password) throws ServiceException;

    User getUser(int id) throws ServiceException;

    List<Track> getUserTracks(int id) throws ServiceException;

    List<Album> getUserAlbums(int id) throws ServiceException;

    List<Playlist> getUserPlaylists(int id) throws ServiceException;

    List<Bonus> getUserBonuses(int id) throws ServiceException;

    List<User> getAllUsers() throws ServiceException;

    boolean updateUserWallet(int id, double wallet) throws ServiceException;

    boolean updateUserUsername(int id, String username) throws ServiceException;

    boolean updateUserPassword(int id, String password) throws ServiceException;

    boolean updateUserRole(int id, Role role) throws ServiceException;

    boolean deleteUser(int id) throws ServiceException;
}
