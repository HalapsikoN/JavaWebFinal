package by.epam.finalTask.service.impl;

import by.epam.finalTask.dao.DAOException;
import by.epam.finalTask.dao.DAOFactory;
import by.epam.finalTask.dao.TrackDAO;
import by.epam.finalTask.entity.Track;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.TrackService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TrackServiceImpl implements TrackService {

    public static final Logger logger= LogManager.getLogger(TrackServiceImpl.class);

    private TrackDAO trackDAO= DAOFactory.getInstance().getSqlTrackDAO();

    @Override
    public List<Track> getAllTracks() throws ServiceException {

        List<Track> trackList;

        try {
            trackList=trackDAO.getAllTracks();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return trackList;
    }
}
