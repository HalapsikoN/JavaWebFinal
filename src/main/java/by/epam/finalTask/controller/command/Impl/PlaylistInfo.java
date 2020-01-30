package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.util.DispatchAssistant;
import by.epam.finalTask.controller.util.JspPageName;
import by.epam.finalTask.controller.util.RequestAttributeName;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.entity.Playlist;
import by.epam.finalTask.entity.logic.PlaylistLogic;
import by.epam.finalTask.service.PlaylistService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PlaylistInfo implements Command {

    private final static Logger logger= LogManager.getLogger(AlbumInfo.class);

    private final static PlaylistService playlistService = ServiceFactory.getInstance().getPlaylistService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {
            int albumId=Integer.valueOf(req.getParameter(RequestParameterName.PLAYLIST_ID));

            Playlist playlist= playlistService.getPlaylist(albumId);

            req.setAttribute(RequestAttributeName.PLAYLIST, playlist);

            double playlistPrice= PlaylistLogic.getAlbumPrice(playlist);

            req.setAttribute(RequestAttributeName.PLAYLIST_PRICE, playlistPrice);

            DispatchAssistant.forwardToJsp(req, resp, JspPageName.PLAYLIST_INFO);
        } catch (ServiceException | IOException | ServletException e) {
            logger.error(e);
            throw new CommandException(e);
        }
    }
}
