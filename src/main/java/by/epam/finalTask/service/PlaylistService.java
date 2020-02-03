package by.epam.finalTask.service;

import by.epam.finalTask.entity.Playlist;

import java.util.List;

public interface PlaylistService {

    boolean addPlaylist(Playlist playlist) throws ServiceException;

    Playlist getPlaylist(int id) throws ServiceException;

    boolean updatePlaylist(int id, Playlist playlist) throws ServiceException;
    boolean updatePlaylistTracks(int playlistId, List<Integer> trackListId) throws ServiceException;

    boolean deletePlaylist(int id) throws ServiceException;

    List<Playlist> getAllPlaylists() throws ServiceException;

}
