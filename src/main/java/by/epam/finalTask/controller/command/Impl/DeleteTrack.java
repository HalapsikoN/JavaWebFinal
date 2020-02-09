package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import by.epam.finalTask.service.TrackService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class DeleteTrack implements Command {

    private final static Logger logger = LogManager.getLogger(DeleteUser.class);

    private final static TrackService trackService = ServiceFactory.getInstance().getTrackService();

    private final static String ERROR_MSG="locale.deleteTrack.errorMsg";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            int trackId = Integer.valueOf(req.getParameter(RequestParameterName.TRACK_ID));

            boolean isDeleted = trackService.deleteTrack(trackId);
            String message;
            Locale locale= new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
           // Command command;
            if (isDeleted) {
              //  command = CommandProvider.getInstance().getCommand(CommandName.MAIN_PAGE.name());
                DispatchAssistant.redirectToCommand(req, resp, CommandName.MAIN_PAGE);
            } else {
                //req.setAttribute(RequestAttributeName.MESSAGE, FAILED_MSG);
               // command = CommandProvider.getInstance().getCommand(CommandName.EDIT_TRACK_PAGE.name());
                message=ResourceManager.getString(ERROR_MSG, locale);
                DispatchAssistant.redirectToCommand(req, resp, CommandName.EDIT_TRACK_PAGE, message);
            }

            //command.execute(req, resp);
        } catch (ServiceException| ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }
}
