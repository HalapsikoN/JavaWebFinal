package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.util.DispatchAssistant;
import by.epam.finalTask.controller.util.JspPageName;
import by.epam.finalTask.controller.util.RequestAttributeName;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.entity.Album;
import by.epam.finalTask.entity.Comment;
import by.epam.finalTask.entity.logic.AlbumLogic;
import by.epam.finalTask.service.AlbumService;
import by.epam.finalTask.service.CommentService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AlbumInfo implements Command {

    private final static Logger logger= LogManager.getLogger(AlbumInfo.class);

    private final static AlbumService albumService= ServiceFactory.getInstance().getAlbumService();
    private static final CommentService commentService= ServiceFactory.getInstance().getCommentService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {
            int albumId=Integer.valueOf(req.getParameter(RequestParameterName.ALBUM_ID));

            Album album=albumService.getAlbum(albumId);

            req.setAttribute(RequestAttributeName.ALBUM, album);

            double albumPrice= AlbumLogic.getAlbumPrice(album);

            req.setAttribute(RequestAttributeName.ALBUM_PRICE, albumPrice);

            List<Comment> commentList=commentService.getAllComments();

            req.setAttribute(RequestAttributeName.COMMENT_LIST, commentList);

            DispatchAssistant.forwardToJsp(req, resp, JspPageName.ALBUM_INFO);
        } catch (ServiceException | IOException | ServletException e) {
            logger.error(e);
            throw new CommandException(e);
        }
    }
}
