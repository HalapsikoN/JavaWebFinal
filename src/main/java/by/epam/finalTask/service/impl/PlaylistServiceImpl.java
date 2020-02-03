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

    public static final Logger logger= LogManager.getLogger(PlaylistServiceImpl.class);

    private PlaylistDAO playlistDAO = DAOFactory.getInstance().getSqlPlaylistDAO();

    @Override
    public boolean addPlaylist(Playlist playlist) throws ServiceException {
        boolean result;

        try{
            result=playlistDAO.addPlaylistWithOutTracks(playlist);
        }catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public Playlist getPlaylist(int id) throws ServiceException {
        Playlist playlist;

        try{
            playlist=playlistDAO.getPlaylistById(id);
        }catch (DAOException e) {
            throw new ServiceException(e);
        }

        return playlist;
    }

    @Override
    public boolean updatePlaylist(int id, Playlist playlist) throws ServiceException {
        boolean result;

        try{
            result=playlistDAO.updatePlaylistById(id, playlist);
        }catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean updatePlaylistTracks(int playlistId, List<Integer> trackListId) throws ServiceException {
        boolean result = true;

        try {
            playlistDAO.deletePlaylistTracks(playlistId);
            for (Integer trackId : trackListId) {
                System.out.println(trackId);
                if (!playlistDAO.addTrackToPlaylistById(playlistId, trackId)) {
                    result = false;
                }
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean deletePlaylist(int id) throws ServiceException {
        boolean result;

        try{
            result=playlistDAO.deletePlaylistById(id);
        }catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public List<Playlist> getAllPlaylists() throws ServiceException {
        List<Playlist> playlistList;

        try {
            playlistList= playlistDAO.getAllPlaylists();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return playlistList;
    }
}
