package by.epam.finalTask.service;

import by.epam.finalTask.service.impl.*;

public class ServiceFactory {

    private static final ServiceFactory instance=new ServiceFactory();

    private final UserService userService=new UserServiceImpl();
    private final TrackService trackService=new TrackServiceImpl();
    private final AlbumService albumService=new AlbumServiceImpl();
    private final PlaylistService playlistService=new PlaylistServiceImpl();
    private final CommentService commentService=new CommentServiceImpl();

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

    public CommentService getCommentService() {
        return commentService;
    }
}
