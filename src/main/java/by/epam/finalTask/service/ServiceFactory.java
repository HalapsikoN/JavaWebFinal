package by.epam.finalTask.service;

import by.epam.finalTask.service.impl.AlbumServiceImpl;
import by.epam.finalTask.service.impl.PlaylistServiceImpl;
import by.epam.finalTask.service.impl.TrackServiceImpl;
import by.epam.finalTask.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory instance=new ServiceFactory();

    private final UserService userService=new UserServiceImpl();
    private final TrackService trackService=new TrackServiceImpl();
    private final AlbumService albumService=new AlbumServiceImpl();
    private final PlaylistService playlistService=new PlaylistServiceImpl();

    private ServiceFactory(){
    }

    public static ServiceFactory getInstance(){
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public TrackService getTrackService() {
        return trackService;
    }

    public AlbumService getAlbumService() {
        return albumService;
    }

    public PlaylistService getPlaylistService() {
        return playlistService;
    }
}
