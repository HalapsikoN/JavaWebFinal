package by.epam.finalTask.dao;

import by.epam.finalTask.entity.Album;
import by.epam.finalTask.entity.Track;

import java.util.List;

public interface AlbumDAO {
    boolean addAlbumWithoutTracks(Album album) throws DAOException;
    boolean addTrackToAlbum(Album album, Track track) throws DAOException;
    boolean addTrackToAlbumById(int album_id, int track_id) throws DAOException;

    Album getAlbumById(int id) throws DAOException;

    boolean updateAlbumById(int id, Album album) throws DAOException;

    boolean deleteAlbumBId(int id) throws DAOException;
    boolean deleteAlbumTracks(int id) throws DAOException;

    List<Album> getAllAlbums() throws DAOException;
}
