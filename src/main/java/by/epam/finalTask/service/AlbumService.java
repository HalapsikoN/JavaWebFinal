package by.epam.finalTask.service;

import by.epam.finalTask.entity.Album;

import java.util.List;

public interface AlbumService {

    Album getAlbum(int id) throws ServiceException;

    List<Album> getAllAlbums() throws ServiceException;
}
