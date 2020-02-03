package by.epam.finalTask.service;

import by.epam.finalTask.entity.Album;
import by.epam.finalTask.entity.Track;

import java.util.List;

public interface AlbumService {

    boolean addAlbum(Album album) throws ServiceException;

    Album getAlbum(int id) throws ServiceException;

    boolean updateAlbum(int albumId, Album album) throws ServiceException;
    boolean updateAlbumTracks(int albumId, List<Integer> trackListId) throws ServiceException;

    boolean deleteAlbum(int albumId) throws ServiceException;

    List<Album> getAllAlbums() throws ServiceException;
}
