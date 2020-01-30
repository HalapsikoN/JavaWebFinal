package by.epam.finalTask.service.impl;

import by.epam.finalTask.dao.DAOException;
import by.epam.finalTask.dao.DAOFactory;
import by.epam.finalTask.dao.PlaylistDAO;
import by.epam.finalTask.entity.Playlist;
import by.epam.finalTask.service.PlaylistService;
import by.epam.finalTask.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PlaylistServiceImpl implements PlaylistService {

    public static final Logger logger= LogManager.getLogger(TrackServiceImpl.class);

    private PlaylistDAO albumDAO = DAOFactory.getInstance().getSqlPlaylistDAO();

    @Override
    public List<Playlist> getAllPlaylists() throws ServiceException {
        List<Playlist> playlistList;

        try {
            playlistList= albumDAO.getAllAlbums();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return playlistList;
    }
}
