package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.controller.util.SessionAttributeName;
import by.epam.finalTask.controller.util.SessionHelper;
import by.epam.finalTask.entity.Comment;
import by.epam.finalTask.service.CommentService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddComment implements Command {

    private static final Logger logger= LogManager.getLogger(AddComment.class);

    private static final CommentService commentService= ServiceFactory.getInstance().getCommentService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = SessionHelper.getExistingSession(req);

        if (session == null) {
            throw new CommandException("no session");
        }

        int userId = (int) session.getAttribute(SessionAttributeName.ID);

        int trackId=Integer.valueOf(req.getParameter(RequestParameterName.TRACK_ID));

        GregorianCalendar date=new GregorianCalendar();

        String text=req.getParameter(RequestParameterName.TEXT);

        System.out.println(text);

        Comment comment=new Comment(userId, date, trackId, text);

        boolean isAdded= false;
        try {
            isAdded = commentService.addComment(comment);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        if(!isAdded){
            throw new CommandException("Comment is not saved");
        }

        Command command= CommandProvider.getInstance().getCommand(CommandName.MAIN_PAGE.name());
        command.execute(req, resp);
    }
}
