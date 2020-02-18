package by.epam.finalTask.dao;

import by.epam.finalTask.entity.Track;

import java.util.List;

public interface TrackDAO {

    boolean addTrack(Track track) throws DAOException;

    Track getTrackById(int id) throws DAOException;
    Track getTrackByName(String name) throws DAOException;

    boolean updateTrackById(int id, Track track) throws DAOException;

    boolean deleteTrackById(int id) throws DAOException;

    List<Track> getAllTracks() throws DAOException;
    List<Track> getTracksWithArtist(String artist) throws DAOException;

    boolean isAlreadyHaveFilename(String filename) throws DAOException;

    int getNumberOfTracks() throws DAOException;
    List<Track> getTracksFromOffset(int offset, int numberOfElements) throws DAOException;
}
