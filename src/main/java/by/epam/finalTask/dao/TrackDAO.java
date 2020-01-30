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
}
