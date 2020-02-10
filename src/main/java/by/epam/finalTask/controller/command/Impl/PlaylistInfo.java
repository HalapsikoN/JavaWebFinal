package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.entity.Comment;
import by.epam.finalTask.entity.Playlist;
import by.epam.finalTask.entity.logic.PlaylistLogic;
import by.epam.finalTask.service.CommentService;
import by.epam.finalTask.service.PlaylistService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PlaylistInfo implements Command {

    private final static Logger logger = LogManager.getLogger(AlbumInfo.class);

    private final static PlaylistService playlistService = ServiceFactory.getInstance().getPlaylistService();
    private static final CommentService commentService = ServiceFactory.getInstance().getCommentService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {
            int playlistId = RequestDataExecutor.getIntegerByName(RequestParameterName.PLAYLIST_ID, req);

            Playlist playlist = playlistService.getPlaylist(playlistId);

            req.setAttribute(RequestAttributeName.PLAYLIST, playlist);

            double playlistPrice = PlaylistLogic.getAlbumPrice(playlist);

            req.setAttribute(RequestAttributeName.PLAYLIST_PRICE, playlistPrice);

            List<Comment> commentList = commentService.getAllComments();

            req.setAttribute(RequestAttributeName.COMMENT_LIST, commentList);

            DispatchAssistant.forwardToJsp(req, resp, JspPageName.PLAYLIST_INFO);
        } catch (ServiceException | IOException | ServletException e) {
            logger.error(e);
            throw new CommandException(e);
        }
    }
}
