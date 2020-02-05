package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.RequestAttributeName;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.controller.util.SessionAttributeName;
import by.epam.finalTask.controller.util.SessionHelper;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import by.epam.finalTask.service.TrackService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteTrack implements Command {

    private final static Logger logger = LogManager.getLogger(DeleteUser.class);

    private final static TrackService trackService = ServiceFactory.getInstance().getTrackService();

    private final static String FAILED_MSG="Track has not been deleted!";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            int trackId = Integer.valueOf(req.getParameter(RequestParameterName.TRACK_ID));

            boolean isDeleted = trackService.deleteTrack(trackId);

            Command command;
            if (isDeleted) {
                command = CommandProvider.getInstance().getCommand(CommandName.MAIN_PAGE.name());
            } else {
                req.setAttribute(RequestAttributeName.MESSAGE, FAILED_MSG);
                command = CommandProvider.getInstance().getCommand(CommandName.EDIT_TRACK_PAGE.name());
            }

            command.execute(req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }
}
