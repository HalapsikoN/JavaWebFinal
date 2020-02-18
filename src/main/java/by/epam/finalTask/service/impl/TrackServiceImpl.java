package by.epam.finalTask.service.impl;

import by.epam.finalTask.dao.DAOException;
import by.epam.finalTask.dao.DAOFactory;
import by.epam.finalTask.dao.TrackDAO;
import by.epam.finalTask.entity.Track;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.TrackService;
import by.epam.finalTask.service.validator.TrackDataValidator;

import java.util.ArrayList;
import java.util.List;

public class TrackServiceImpl implements TrackService {

    private TrackDAO trackDAO = DAOFactory.getInstance().getSqlTrackDAO();

    @Override
    public boolean addTrack(Track track) throws ServiceException {
        boolean result;

        if (!TrackDataValidator.isValidTrack(track)) {
            throw new ServiceException("Not valid data track");
        }

        try {
            result = trackDAO.addTrack(track);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public Track getTrack(int id) throws ServiceException {
        Track track;

        try {
            track = trackDAO.getTrackById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return track;
    }

    @Override
    public boolean updateTrack(int id, Track track) throws ServiceException {
        boolean result;

        if (!TrackDataValidator.isValidTrack(track)) {
            throw new ServiceException("Not valid data track");
        }

        try {
            result = trackDAO.updateTrackById(id, track);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean deleteTrack(int id) throws ServiceException {
        boolean result;

        try {
            result = trackDAO.deleteTrackById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public List<Track> getAllTracks() throws ServiceException {
        List<Track> trackList;

        try {
            trackList = trackDAO.getAllTracks();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return trackList;
    }

    @Override
    public List<Track> getTracksWithArtist(String artist) throws ServiceException {
        List<Track> trackList;

        try {
            trackList = trackDAO.getTracksWithArtist(artist);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return trackList;
    }

    @Override
    public boolean isAlreadyHaveFilename(String filename) throws ServiceException {
        boolean result;

        if (!TrackDataValidator.isValidFilename(filename)) {
            throw new ServiceException("Not valid data track");
        }

        try {
            result = trackDAO.isAlreadyHaveFilename(filename);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public List<Integer> getPageArray(int numberAtOnePage) throws ServiceException {
        List<Integer> list = new ArrayList<>();

        int numberOfElements;
        try {
            numberOfElements = trackDAO.getNumberOfTracks();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        int numberOfPage = (numberOfElements % numberAtOnePage == 0) ? (numberOfElements / numberAtOnePage) : (numberOfElements / numberAtOnePage + 1);

        for (int i = 1; i <= (numberOfPage); ++i) {
            list.add(i);
        }

        return list;
    }

    @Override
    public List<Track> getTracksOfPage(int page, int numberOfElements) throws ServiceException {
        List<Track> trackList;

        int offset = (page - 1) * numberOfElements;

        try {
            trackList = trackDAO.getTracksFromOffset(offset, numberOfElements);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return trackList;
    }
}
