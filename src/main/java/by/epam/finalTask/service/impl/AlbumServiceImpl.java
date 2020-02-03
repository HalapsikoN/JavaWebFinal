package by.epam.finalTask.service.impl;

import by.epam.finalTask.dao.AlbumDAO;
import by.epam.finalTask.dao.DAOException;
import by.epam.finalTask.dao.DAOFactory;
import by.epam.finalTask.entity.Album;
import by.epam.finalTask.entity.Track;
import by.epam.finalTask.service.AlbumService;
import by.epam.finalTask.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AlbumServiceImpl implements AlbumService {

    public static final Logger logger = LogManager.getLogger(TrackServiceImpl.class);

    private AlbumDAO albumDAO = DAOFactory.getInstance().getSqlAlbumDAO();

    @Override
    public boolean addAlbum(Album album) throws ServiceException {
        boolean result;

        try {
            result = albumDAO.addAlbumWithoutTracks(album);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public Album getAlbum(int id) throws ServiceException {
        Album album;

        try {
            album = albumDAO.getAlbumById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return album;
    }

    @Override
    public boolean updateAlbum(int albumId, Album album) throws ServiceException {
        boolean result;

        try {
            result = albumDAO.updateAlbumById(albumId, album);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean updateAlbumTracks(int albumId, List<Integer> trackListId) throws ServiceException {
        boolean result = true;

        try {
            albumDAO.deleteAlbumTracks(albumId);
            for (Integer trackId : trackListId) {
                System.out.println(trackId);
                if (!albumDAO.addTrackToAlbumById(albumId, trackId)) {
                    result = false;
                }
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean deleteAlbum(int albumId) throws ServiceException {
        boolean result;

        try {
            result = albumDAO.deleteAlbumBId(albumId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public List<Album> getAllAlbums() throws ServiceException {
        List<Album> albumList;

        try {
            albumList = albumDAO.getAllAlbums();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return albumList;
    }
}
