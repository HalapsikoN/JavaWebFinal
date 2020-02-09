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

public class AddPlaylist implements Command {

    private static final Logger logger= LogManager.getLogger(AddPlaylist.class);

    private static final PlaylistService playlistService = ServiceFactory.getInstance().getPlaylistService();

    private static final String ERROR_MSG="locale.addPlaylist.errorMsg";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            String name= RequestDataExecutor.getStringWithWriteEncoding(req, RequestParameterName.NAME);
            GregorianCalendar calendar=new GregorianCalendar();

            Playlist playlist=new Playlist(name, calendar);

            boolean isAdded= playlistService.addPlaylist(playlist);
            Locale locale= new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
            String message;
            //Command command;
            if(isAdded){
                //command= CommandProvider.getInstance().getCommand(CommandName.PLAYLISTS_PAGE.name());
                DispatchAssistant.redirectToCommand(req, resp, CommandName.PLAYLISTS_PAGE);
            }else {
                //command= CommandProvider.getInstance().getCommand(CommandName.ADD_PLAYLIST_PAGE.name());
                message=ResourceManager.getString(ERROR_MSG, locale);
                //req.setAttribute(RequestAttributeName.MESSAGE, ERROR_MSG);
                DispatchAssistant.redirectToCommand(req, resp, CommandName.PLAYLISTS_PAGE, message);
            }

            //command.execute(req, resp);
        } catch (ServiceException| ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }
}
