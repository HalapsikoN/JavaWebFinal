package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.entity.Track;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import by.epam.finalTask.service.TrackService;
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

public class AddTrack implements Command {

    private static final Logger logger= LogManager.getLogger(AddTrack.class);

    private static final TrackService trackService= ServiceFactory.getInstance().getTrackService();

    private static final String ERROR_MSG="locale.addTrack.errorMsg";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            String name= RequestDataExecutor.getStringWithWriteEncoding(req, RequestParameterName.NAME);
            String artist=RequestDataExecutor.getStringWithWriteEncoding(req, RequestParameterName.ARTIST);
            GregorianCalendar calendar=new GregorianCalendar();
            int year=Integer.valueOf(req.getParameter(RequestParameterName.DATE));
            calendar.set(Calendar.YEAR, year);
            double price=Double.valueOf(req.getParameter(RequestParameterName.PRICE));

            Track track=new Track(name, artist, calendar, price);

            boolean isAdded=trackService.addTrack(track);
            Locale locale= new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
            String message;
            //Command command;
            if(isAdded){
                //command= CommandProvider.getInstance().getCommand(CommandName.MAIN_PAGE.name());
                DispatchAssistant.redirectToCommand(req, resp, CommandName.MAIN_PAGE);
            }else {
                //command= CommandProvider.getInstance().getCommand(CommandName.ADD_TRACK_PAGE.name());
               // req.setAttribute(RequestAttributeName.MESSAGE, ERROR_MSG);
                message=ResourceManager.getString(ERROR_MSG, locale);
                DispatchAssistant.redirectToCommand(req, resp, CommandName.MAIN_PAGE, message);
            }

            //command.execute(req, resp);
        } catch (ServiceException| ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }
}
