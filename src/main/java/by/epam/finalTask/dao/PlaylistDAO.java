package by.epam.finalTask.dao;

import by.epam.finalTask.entity.Playlist;
import by.epam.finalTask.entity.Track;

public interface PlaylistDAO {
    boolean addPlaylistWithOutTracks(Playlist playlist) throws DAOException;
    boolean addTrackToPlaylist(Playlist playlist, Track track) throws DAOException;
    boolean addTrackToPlaylistById(int playlist_id, int track_id) throws DAOException;

    Playlist getPlaylistById(int id) throws DAOException;

    boolean updatePlaylistById(int id, Playlist playlist) throws DAOException;

    boolean deletePlaylistBId(int id) throws DAOException;
}
