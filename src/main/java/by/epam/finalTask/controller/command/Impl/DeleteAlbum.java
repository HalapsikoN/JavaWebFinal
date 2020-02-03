package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.RequestAttributeName;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.service.AlbumService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAlbum implements Command {

    private final static Logger logger = LogManager.getLogger(DeleteAlbum.class);

    private final static AlbumService albumService = ServiceFactory.getInstance().getAlbumService();

    private final static String FAILED_MSG="Album has not been deleted!";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            int albumId = Integer.valueOf(req.getParameter(RequestParameterName.ALBUM_ID));

            boolean isDeleted = albumService.deleteAlbum(albumId);

            Command command;
            if (isDeleted) {
                command = CommandProvider.getInstance().getCommand(CommandName.ALBUMS_PAGE.name());
                command.execute(req, resp);
            } else {
                req.setAttribute(RequestAttributeName.MESSAGE, FAILED_MSG);
                command = CommandProvider.getInstance().getCommand(CommandName.EDIT_ALBUM_PAGE.name());
                command.execute(req, resp);
            }

            command.execute(req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
    }
}
