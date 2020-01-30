package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.util.DispatchAssistant;
import by.epam.finalTask.controller.util.JspPageName;
import by.epam.finalTask.controller.util.RequestAttributeName;
import by.epam.finalTask.dao.DAOFactory;
import by.epam.finalTask.entity.Album;
import by.epam.finalTask.service.AlbumService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlbumsPage implements Command {

    private static final Logger logger = LogManager.getLogger(AlbumsPage.class);

    private static final AlbumService albumService= ServiceFactory.getInstance().getAlbumService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            List<Album> albumsList;

            albumsList=albumService.getAllAlbums();

            req.setAttribute(RequestAttributeName.ALBUM_LIST, albumsList);

            DispatchAssistant.forwardToJsp(req, resp, JspPageName.ALBUMS_PAGE);
        } catch (ServiceException|IOException|ServletException e) {
            logger.error(e);
            //что-то ещё
            throw new CommandException(e);
        }
    }
}
