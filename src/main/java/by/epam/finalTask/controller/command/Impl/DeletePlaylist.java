package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.service.PlaylistService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class DeletePlaylist implements Command {

    private final static Logger logger = LogManager.getLogger(DeletePlaylist.class);

    private final static PlaylistService playlistService = ServiceFactory.getInstance().getPlaylistService();

    private final static String ERROR_MSG = "locale.deletePlaylist.errorMsg";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            int playlistId = RequestDataExecutor.getIntegerByName(RequestParameterName.PLAYLIST_ID, req);

            boolean isDeleted = playlistService.deletePlaylist(playlistId);
            String message;
            Locale locale = new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
            if (isDeleted) {
                DispatchAssistant.redirectToCommand(req, resp, CommandName.PLAYLISTS_PAGE);
            } else {
                message = ResourceManager.getString(ERROR_MSG, locale);
                DispatchAssistant.redirectToCommand(req, resp, CommandName.EDIT_PLAYLIST_PAGE, message);
            }

        } catch (ServiceException | ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }
    }

}
