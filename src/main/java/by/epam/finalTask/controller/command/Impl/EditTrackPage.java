package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.entity.Track;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import by.epam.finalTask.service.TrackService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditTrackPage implements Command {

    private static final Logger logger = LogManager.getLogger(EditTrackPage.class);

    private static final TrackService trackService = ServiceFactory.getInstance().getTrackService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            int trackId = RequestDataExecutor.getIntegerByName(RequestParameterName.TRACK_ID, req);

            Track track = trackService.getTrack(trackId);

            req.setAttribute(RequestAttributeName.TRACK, track);

            DispatchAssistant.forwardToJsp(req, resp, JspPageName.EDIT_TRACK_PAGE);
        } catch (ServletException | IOException | ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }
}
