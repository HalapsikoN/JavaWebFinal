package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.RequestAttributeName;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.entity.Album;
import by.epam.finalTask.service.AlbumService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddAlbum implements Command {
    private static final Logger logger= LogManager.getLogger(AddAlbum.class);

    private static final AlbumService albumService = ServiceFactory.getInstance().getAlbumService();

    private static final String ERROR_MSG="Album has not been added!";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            String name=req.getParameter(RequestParameterName.NAME);
            String artist=req.getParameter(RequestParameterName.ARTIST);
            GregorianCalendar calendar=new GregorianCalendar();
            int year=Integer.valueOf(req.getParameter(RequestParameterName.DATE));
            calendar.set(Calendar.YEAR, year);

            Album album=new Album(name, artist, calendar);

            boolean isAdded= albumService.addAlbum(album);

            Command command;
            if(isAdded){
                command= CommandProvider.getInstance().getCommand(CommandName.ALBUMS_PAGE.name());
            }else {
                command= CommandProvider.getInstance().getCommand(CommandName.ADD_TRACK_PAGE.name());
                req.setAttribute(RequestAttributeName.MESSAGE, ERROR_MSG);
            }

            command.execute(req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }
}
