package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.entity.Playlist;
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
import java.util.GregorianCalendar;
import java.util.Locale;

public class EditPlaylist implements Command {

    private static final Logger logger = LogManager.getLogger(EditPlaylist.class);

    private static final PlaylistService playlistService = ServiceFactory.getInstance().getPlaylistService();

    private static final String SUCCESS_MSG = "locale.editPlaylist.successMsg";
    private static final String ERROR_MSG = "locale.editPlaylist.errorMsg";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            int playlistId = RequestDataExecutor.getIntegerByName(RequestParameterName.PLAYLIST_ID, req);
            String name = RequestDataExecutor.getStringWithWriteEncoding(req, RequestParameterName.NAME);
            GregorianCalendar calendar = new GregorianCalendar();

            Playlist playlist = new Playlist(name, calendar);

            boolean isUpdated = playlistService.updatePlaylist(playlistId, playlist);
            String message;
            Locale locale = new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
            if (isUpdated) {
                message = ResourceManager.getString(SUCCESS_MSG, locale);
            } else {
                message = ResourceManager.getString(ERROR_MSG, locale);
            }
            DispatchAssistant.redirectToCommand(req, resp, CommandName.PLAYLISTS_PAGE, message);
        } catch (ServiceException | ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }

}
