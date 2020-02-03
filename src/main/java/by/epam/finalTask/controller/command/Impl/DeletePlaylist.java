package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.RequestAttributeName;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.service.PlaylistService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePlaylist implements Command {

    private final static Logger logger = LogManager.getLogger(DeletePlaylist.class);

    private final static PlaylistService playlistService = ServiceFactory.getInstance().getPlaylistService();

    private final static String FAILED_MSG="Playlist has not been deleted!";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            int playlistId = Integer.valueOf(req.getParameter(RequestParameterName.PLAYLIST_ID));

            boolean isDeleted = playlistService.deletePlaylist(playlistId);

            Command command;
            if (isDeleted) {
                command = CommandProvider.getInstance().getCommand(CommandName.PLAYLISTS_PAGE.name());
                command.execute(req, resp);
            } else {
                req.setAttribute(RequestAttributeName.MESSAGE, FAILED_MSG);
                command = CommandProvider.getInstance().getCommand(CommandName.EDIT_PLAYLIST_PAGE.name());
                command.execute(req, resp);
            }

            command.execute(req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
    }

}
