package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.entity.Track;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import by.epam.finalTask.service.TrackService;
import com.sun.org.apache.bcel.internal.classfile.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import static org.apache.logging.log4j.web.WebLoggerContextUtils.getServletContext;

public class AddTrack implements Command {

    private static final Logger logger = LogManager.getLogger(AddTrack.class);

    private static final TrackService trackService = ServiceFactory.getInstance().getTrackService();

    private static final String ERROR_MSG = "locale.addTrack.errorMsg";
    private static final String DEFAULT_FILENAME = "defaultFilename";
    private static final String ALREADY_HAVE_TRACK_PREFIX = "1";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            String uploadPath = req.getServletContext().getRealPath("\\..\\..") + File.separator + ResourceManager.UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String fileName=null;
            for (Part part : req.getParts()) {
                fileName = getFileName(part);
                if(!fileName.equals(DEFAULT_FILENAME)) {

                    while (trackService.isAlreadyHaveFilename(fileName)){
                        fileName=ALREADY_HAVE_TRACK_PREFIX+fileName;
                    }

                    part.write(uploadPath + File.separator + fileName);
                }
            }

            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            String name = RequestDataExecutor.getStringWithWriteEncoding(req, RequestParameterName.NAME);
            String artist = RequestDataExecutor.getStringWithWriteEncoding(req, RequestParameterName.ARTIST);
            GregorianCalendar calendar = new GregorianCalendar();
            int year = Integer.valueOf(req.getParameter(RequestParameterName.DATE));
            calendar.set(Calendar.YEAR, year);
            double price = RequestDataExecutor.getDoubleByName(RequestParameterName.PRICE, req);

            Track track = new Track(name, artist, calendar, price, fileName);

            boolean isAdded = trackService.addTrack(track);

            Locale locale = new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
            String message;

            if (isAdded) {

                DispatchAssistant.redirectToCommand(req, resp, CommandName.MAIN_PAGE);
            } else {
                message = ResourceManager.getString(ERROR_MSG, locale);
                DispatchAssistant.redirectToCommand(req, resp, CommandName.MAIN_PAGE, message);
            }
        } catch (ServiceException | ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return DEFAULT_FILENAME;
    }

}
