package by.epam.finalTask.service.impl;

import by.epam.finalTask.dao.DAOException;
import by.epam.finalTask.dao.DAOFactory;
import by.epam.finalTask.dao.UserDAO;
import by.epam.finalTask.entity.*;
import by.epam.finalTask.entity.util.Role;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.UserService;
import by.epam.finalTask.service.validator.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    public static final Logger logger= LogManager.getLogger(UserServiceImpl.class);

    private UserDAO userDAO= DAOFactory.getInstance().getSqlUserDAO();

    @Override
    public User signIn(String login, String password) throws ServiceException {
        User user=null;

        if(!isValidData(login, password)){
            throw new ServiceException("Not valid data");
        }

        try {
            String realPassword=userDAO.getUserPasswordByLogin(login);
            if(realPassword!=null && BCrypt.checkpw(password, realPassword)){
                user=userDAO.getUserByLogin(login);
            }
        } catch (DAOException e) {
            throw new ServiceException("Cannot perform action with data source", e);
        }

        return user;
    }

    @Override
    public boolean register(User user, String password) throws ServiceException {
        boolean result=false;

        if(!isValidData(user, password)){
            throw new ServiceException("Not valid data");
        }

        try {
            boolean isBusyLogin=(userDAO.getUserByLogin(user.getLogin())!=null);

            password=BCrypt.hashpw(password, BCrypt.gensalt());

            if(!isBusyLogin){
                userDAO.addUser(user, password);
                result=true;
            }

        } catch (DAOException e) {
            throw new ServiceException("Cannot perform action with data source", e);
        }

        return result;
    }

    @Override
    public boolean addTrackToUser(int userId, int trackId) throws ServiceException {
        boolean result=true;

        try {
            result=userDAO.addTrackToUser(userId, trackId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean addAlbumToUser(int userId, int albumId) throws ServiceException {
        boolean result=true;

        try {
            result=userDAO.addAlbumToUser(userId, albumId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean addPlaylistToUser(int userId, int playlistId) throws ServiceException {
        boolean result=true;

        try {
            result=userDAO.addPlaylistToUser(userId, playlistId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public User getUser(int id) throws ServiceException {
        User user;

        try {
            user=userDAO.getUserById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public List<Track> getUserTracks(int id) throws ServiceException {
        List<Track> trackList;

        try {
            trackList=userDAO.getUserTracksById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return trackList;
    }

    @Override
    public List<Album> getUserAlbums(int id) throws ServiceException {
        List<Album> albumList;

        try {
            albumList=userDAO.getUserAlbumsById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return albumList;
    }

    @Override
    public List<Playlist> getUserPlaylists(int id) throws ServiceException {
        List<Playlist> playlistList;

        try {
            playlistList=userDAO.getUserPlayListsById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return playlistList;
    }

    @Override
    public List<Bonus> getUserBonuses(int id) throws ServiceException {
        List<Bonus> bonusList;

        try {
            bonusList=userDAO.getUserBonusesById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return bonusList;
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        List<User> userList;

        try {
            userList=userDAO.getAllUsers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return userList;
    }

    @Override
    public boolean updateUserWallet(int id, double wallet) throws ServiceException {
        boolean result=true;

        try {
            result=userDAO.updateUserWalletById(id, wallet);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean updateUserUsername(int id, String username) throws ServiceException {
        boolean result=true;

        try {
            result=userDAO.updateUserNameById(id, username);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean updateUserPassword(int id, String password) throws ServiceException {
        boolean result=true;

        try {

            password=BCrypt.hashpw(password, BCrypt.gensalt());

            result=userDAO.updateUserPasswordById(id, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean updateUserRole(int id, Role role) throws ServiceException {
        boolean result=true;

        try {
            result=userDAO.updateUserRoleById(id, role);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean deleteUser(int id) throws ServiceException {
        boolean result;

        try {
            result=userDAO.deleteUserById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    private boolean isValidData(String login, String password){
        boolean validLogin= UserDataValidator.isValidLogin(login);
        boolean validPassword=UserDataValidator.isValidPassword(password);
        return (validLogin&&validPassword);
    }

    private boolean isValidData(User user, String password){
        boolean validUser= UserDataValidator.isUserValid(user);
        boolean validPassword=UserDataValidator.isValidPassword(password);
        return (validUser&&validPassword);
    }
}
