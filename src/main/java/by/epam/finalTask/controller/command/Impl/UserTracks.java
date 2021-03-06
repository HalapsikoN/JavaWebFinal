package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.entity.Comment;
import by.epam.finalTask.entity.Track;
import by.epam.finalTask.service.CommentService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import by.epam.finalTask.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserTracks implements Command {

    private final static Logger logger = LogManager.getLogger(UserTracks.class);

    private final static UserService userService = ServiceFactory.getInstance().getUserService();
    private static final CommentService commentService = ServiceFactory.getInstance().getCommentService();

    private static final int NUMBER_ELEMENTS_AT_ONE_PAGE=4;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            int userId = (int) session.getAttribute(SessionAttributeName.ID);

            Integer page= RequestDataExecutor.getIntegerByName(RequestParameterName.PAGE, req);

            List<Integer> pageArray=userService.getUserPageArray(NUMBER_ELEMENTS_AT_ONE_PAGE, userId);

            req.setAttribute(RequestAttributeName.PAGE_ARRAY, pageArray);

            if(page==null || !pageArray.contains(page)){
                page=1;
            }

            req.setAttribute(RequestAttributeName.CURRENT_PAGE, page);

            List<Track> trackList = userService.getUserTracksOfPage(page, NUMBER_ELEMENTS_AT_ONE_PAGE, userId);

            //List<Track> trackList = userService.getUserTracks(userId);

            req.setAttribute(RequestAttributeName.SONG_LIST, trackList);

            List<Comment> commentList = commentService.getAllComments();

            req.setAttribute(RequestAttributeName.COMMENT_LIST, commentList);

            DispatchAssistant.forwardToJsp(req, resp, JspPageName.USER_TRACKS);

        } catch (ServiceException | ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }
}
