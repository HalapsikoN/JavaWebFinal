package by.epam.finalTask.service;

import by.epam.finalTask.entity.Album;
import by.epam.finalTask.entity.Playlist;
import by.epam.finalTask.entity.Track;
import by.epam.finalTask.entity.User;

import java.util.List;

public interface UserService {

    User signIn(String login, String password) throws ServiceException;

    boolean register(User user, String password) throws ServiceException;

    List<Track> getUserTracks(int id) throws ServiceException;

    List<Album> getUserAlbums(int id) throws ServiceException;

    List<Playlist> getUserPlaylists(int id) throws ServiceException;
}
