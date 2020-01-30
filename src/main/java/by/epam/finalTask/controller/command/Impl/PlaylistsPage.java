package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.util.DispatchAssistant;
import by.epam.finalTask.controller.util.JspPageName;
import by.epam.finalTask.controller.util.RequestAttributeName;
import by.epam.finalTask.entity.Playlist;
import by.epam.finalTask.service.PlaylistService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PlaylistsPage implements Command {
    private static final Logger logger = LogManager.getLogger(AlbumsPage.class);

    private static final PlaylistService playlistService = ServiceFactory.getInstance().getPlaylistService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            List<Playlist> albumsList;

            albumsList= playlistService.getAllPlaylists();

            req.setAttribute(RequestAttributeName.PLAYLIST_LIST, albumsList);


            DispatchAssistant.forwardToJsp(req, resp, JspPageName.PLAYLISTS_PAGE);
        } catch (ServiceException | IOException | ServletException e) {
            logger.error(e);
            //что-то ещё
            throw new CommandException(e);
        }
    }
}
