package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.entity.Album;
import by.epam.finalTask.entity.Track;
import by.epam.finalTask.service.AlbumService;
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

public class EditAlbumPage implements Command {

    private static final Logger logger= LogManager.getLogger(AddTrackPage.class);

    private static final AlbumService albumService = ServiceFactory.getInstance().getAlbumService();
    private static final TrackService trackService = ServiceFactory.getInstance().getTrackService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            int albumId= RequestDataExecutor.getIntegerByName(RequestParameterName.ALBUM_ID, req);

            Album album= albumService.getAlbum(albumId);

            req.setAttribute(RequestAttributeName.ALBUM, album);

            List<Track> trackList=trackService.getTracksWithArtist(album.getArtist());

            req.setAttribute(RequestAttributeName.TRACK_LIST, trackList);

            DispatchAssistant.forwardToJsp(req, resp, JspPageName.EDIT_ALBUM_PAGE);
        } catch (ServletException | IOException | ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }

}
