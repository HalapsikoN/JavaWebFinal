package by.epam.finalTask.service;

import by.epam.finalTask.entity.Track;

import java.util.List;

public interface TrackService {

    boolean addTrack(Track track) throws ServiceException;

    Track getTrack(int id) throws ServiceException;

    boolean updateTrack(int id, Track track) throws ServiceException;

    boolean deleteTrack(int id) throws ServiceException;

    List<Track> getAllTracks() throws ServiceException;

    List<Track> getTracksWithArtist(String artist) throws ServiceException;

    boolean isAlreadyHaveFilename(String filename) throws ServiceException;
}
