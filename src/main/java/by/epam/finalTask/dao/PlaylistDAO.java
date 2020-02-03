package by.epam.finalTask.dao;

import by.epam.finalTask.entity.Playlist;
import by.epam.finalTask.entity.Track;

import java.util.List;

public interface PlaylistDAO {
    boolean addPlaylistWithOutTracks(Playlist playlist) throws DAOException;
    boolean addTrackToPlaylist(Playlist playlist, Track track) throws DAOException;
    boolean addTrackToPlaylistById(int playlist_id, int track_id) throws DAOException;

    Playlist getPlaylistById(int id) throws DAOException;

    boolean updatePlaylistById(int id, Playlist playlist) throws DAOException;

    boolean deletePlaylistById(int id) throws DAOException;
    boolean deletePlaylistTracks(int id) throws DAOException;

    List<Playlist> getAllPlaylists() throws DAOException;
}
