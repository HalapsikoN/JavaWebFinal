package by.epam.finalTask.service;

import by.epam.finalTask.entity.Playlist;

import java.util.List;

public interface PlaylistService {

    Playlist getPlaylist(int id) throws ServiceException;

    List<Playlist> getAllPlaylists() throws ServiceException;

}
