package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.entity.Album;
import by.epam.finalTask.service.AlbumService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AddAlbum implements Command {
    private static final Logger logger= LogManager.getLogger(AddAlbum.class);

    private static final AlbumService albumService = ServiceFactory.getInstance().getAlbumService();

    private static final String ERROR_MSG="locale.addAlbum.errorMsg";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            System.out.println(req.getParameter(RequestParameterName.NAME));
            String name= RequestDataExecutor.getStringWithWriteEncoding(req, RequestParameterName.NAME);
            String artist=RequestDataExecutor.getStringWithWriteEncoding(req, RequestParameterName.ARTIST);
            GregorianCalendar calendar=new GregorianCalendar();
            int year=Integer.valueOf(req.getParameter(RequestParameterName.DATE));
            calendar.set(Calendar.YEAR, year);

            Album album=new Album(name, artist, calendar);

            boolean isAdded= albumService.addAlbum(album);
            Locale locale= new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
            String message;
            //Command command;
            if(isAdded){
                //command= CommandProvider.getInstance().getCommand(CommandName.ALBUMS_PAGE.name());
                DispatchAssistant.redirectToCommand(req, resp, CommandName.ALBUMS_PAGE);
            }else {
                //command= CommandProvider.getInstance().getCommand(CommandName.ADD_TRACK_PAGE.name());
                message=ResourceManager.getString(ERROR_MSG, locale);
                req.setAttribute(RequestAttributeName.MESSAGE, message);
                DispatchAssistant.redirectToCommand(req, resp, CommandName.ALBUMS_PAGE, message);
            }

            //command.execute(req, resp);
        } catch (ServiceException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
