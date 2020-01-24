package by.epam.finalTask.dao;

import by.epam.finalTask.entity.Album;
import by.epam.finalTask.entity.Track;

public interface AlbumDAO {
    boolean addAlbumWithOutTracks(Album album) throws DAOException;
    boolean addTrackToAlbum(Album album, Track track) throws DAOException;
    boolean addTrackToAlbumById(int album_id, int track_id) throws DAOException;

    Album getAlbumById(int id) throws DAOException;

    boolean updateAlbumById(int id, Album album) throws DAOException;

    boolean deleteAlbumBId(int id) throws DAOException;
}
