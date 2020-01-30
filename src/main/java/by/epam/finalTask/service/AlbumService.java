package by.epam.finalTask.service;

import by.epam.finalTask.entity.Album;

import java.util.List;

public interface AlbumService {

    List<Album> getAllAlbums() throws ServiceException;
}
