package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.service.AlbumService;
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

public class DeleteAlbum implements Command {

    private final static Logger logger = LogManager.getLogger(DeleteAlbum.class);

    private final static AlbumService albumService = ServiceFactory.getInstance().getAlbumService();

    private final static String ERROR_MSG = "locale.deleteAlbum.errorMsg";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            int albumId = RequestDataExecutor.getIntegerByName(RequestParameterName.ALBUM_ID, req);

            boolean isDeleted = albumService.deleteAlbum(albumId);
            String message;
            Locale locale = new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
            if (isDeleted) {
                DispatchAssistant.redirectToCommand(req, resp, CommandName.ALBUMS_PAGE);
            } else {
                message = ResourceManager.getString(ERROR_MSG, locale);
                DispatchAssistant.redirectToCommand(req, resp, CommandName.EDIT_ALBUM_PAGE, message);
            }

        } catch (ServiceException | ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }
    }
}
