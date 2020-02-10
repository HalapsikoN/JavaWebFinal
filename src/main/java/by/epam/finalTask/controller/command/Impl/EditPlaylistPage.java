package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.entity.Playlist;
import by.epam.finalTask.entity.Track;
import by.epam.finalTask.service.PlaylistService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import by.epam.finalTask.service.TrackService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditPlaylistPage implements Command {

    private static final Logger logger= LogManager.getLogger(EditPlaylistPage.class);

    private static final PlaylistService playlistService = ServiceFactory.getInstance().getPlaylistService();
    private static final TrackService trackService = ServiceFactory.getInstance().getTrackService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            int playlistId= RequestDataExecutor.getIntegerByName(RequestParameterName.PLAYLIST_ID, req);

            Playlist playlist= playlistService.getPlaylist(playlistId);

            req.setAttribute(RequestAttributeName.PLAYLIST, playlist);

            List<Track> trackList=trackService.getAllTracks();

            req.setAttribute(RequestAttributeName.TRACK_LIST, trackList);

            DispatchAssistant.forwardToJsp(req, resp, JspPageName.EDIT_PLAYLIST_PAGE);
        } catch (ServletException | IOException | ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }

}
