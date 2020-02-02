package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.util.DispatchAssistant;
import by.epam.finalTask.controller.util.JspPageName;
import by.epam.finalTask.controller.util.RequestAttributeName;
import by.epam.finalTask.entity.Comment;
import by.epam.finalTask.entity.Track;
import by.epam.finalTask.service.CommentService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import by.epam.finalTask.service.TrackService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MainPage implements Command {

    private static final Logger logger = LogManager.getLogger(MainPage.class);

    private static final TrackService trackService= ServiceFactory.getInstance().getTrackService();
    private static final CommentService commentService= ServiceFactory.getInstance().getCommentService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {

            List<Track> trackList=trackService.getAllTracks();

            req.setAttribute(RequestAttributeName.SONG_LIST, trackList);

            List<Comment> commentList=commentService.getAllComments();

            req.setAttribute(RequestAttributeName.COMMENT_LIST, commentList);

            DispatchAssistant.forwardToJsp(req, resp, JspPageName.MAIN_PAGE);
        } catch (ServletException | IOException | ServiceException e) {
            logger.error(e);
            //что-то ещё
            throw new CommandException(e);
        }
    }
}
