package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.RequestAttributeName;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.entity.Playlist;
import by.epam.finalTask.service.PlaylistService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class EditPlaylist implements Command {

    private static final Logger logger= LogManager.getLogger(EditPlaylist.class);

    private static final PlaylistService playlistService = ServiceFactory.getInstance().getPlaylistService();

    private static final String SUCCESS_MSG="Playlist has been updated!";
    private static final String ERROR_MSG="Playlist has not been updated!";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            int playlistId=Integer.valueOf(req.getParameter(RequestParameterName.PLAYLIST_ID));
            String name=req.getParameter(RequestParameterName.NAME);
            GregorianCalendar calendar=new GregorianCalendar();

            Playlist playlist=new Playlist(name, calendar);

            boolean isUpdated= playlistService.updatePlaylist(playlistId, playlist);

            if(isUpdated){
                req.setAttribute(RequestAttributeName.MESSAGE, SUCCESS_MSG);
            }else {
                req.setAttribute(RequestAttributeName.MESSAGE, ERROR_MSG);
            }
            Command command= CommandProvider.getInstance().getCommand(CommandName.EDIT_PLAYLIST_PAGE.name());
            command.execute(req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }

}
