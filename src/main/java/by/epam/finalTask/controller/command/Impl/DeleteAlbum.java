package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
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

    private final static String ERROR_MSG="locale.deleteAlbum.errorMsg";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            int albumId = Integer.valueOf(req.getParameter(RequestParameterName.ALBUM_ID));

            boolean isDeleted = albumService.deleteAlbum(albumId);
            String message;
            Locale locale= new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
            //Command command;
            if (isDeleted) {
                //command = CommandProvider.getInstance().getCommand(CommandName.ALBUMS_PAGE.name());
                //command.execute(req, resp);
                DispatchAssistant.redirectToCommand(req, resp, CommandName.ALBUMS_PAGE);
            } else {
                //req.setAttribute(RequestAttributeName.MESSAGE, FAILED_MSG);
               //command = CommandProvider.getInstance().getCommand(CommandName.EDIT_ALBUM_PAGE.name());
                //command.execute(req, resp);
                message= ResourceManager.getString(ERROR_MSG, locale);
                DispatchAssistant.redirectToCommand(req, resp, CommandName.EDIT_ALBUM_PAGE, message);
            }

            //command.execute(req, resp);
        } catch (ServiceException| ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }
    }
}
