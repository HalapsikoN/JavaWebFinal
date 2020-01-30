package by.epam.finalTask.service;

import by.epam.finalTask.entity.Track;

import java.util.List;

public interface TrackService {

    List<Track> getAllTracks() throws ServiceException;
}
