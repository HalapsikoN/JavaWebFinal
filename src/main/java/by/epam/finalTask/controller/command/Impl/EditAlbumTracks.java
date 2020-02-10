package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.*;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class EditAlbumTracks implements Command {

    private static final Logger logger= LogManager.getLogger(EditAlbumTracks.class);

    private static final AlbumService albumService= ServiceFactory.getInstance().getAlbumService();

    private static final String SUCCESS_MSG="locale.editAlbumTracks.successMsg";
    private static final String ERROR_MSG="locale.editAlbumTracks.errorMsg";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {
            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            int albumId = RequestDataExecutor.getIntegerByName(RequestParameterName.ALBUM_ID, req);

            String[] tracksUpdate = req.getParameterValues(RequestParameterName.TRACKS_UPDATE);

            List<Integer> trackIdList = new ArrayList<>();
            for (String track : tracksUpdate) {
                trackIdList.add(Integer.valueOf(track));
            }

            boolean isUpdated = albumService.updateAlbumTracks(albumId, trackIdList);
            String message;
            Locale locale= new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
            if (isUpdated) {
                //req.setAttribute(RequestAttributeName.MESSAGE, SUCCESS_MSG);
                message= ResourceManager.getString(SUCCESS_MSG, locale);
            } else {
                //req.setAttribute(RequestAttributeName.MESSAGE, ERROR_MSG);
                message=ResourceManager.getString(ERROR_MSG, locale);
            }
//            Command command = CommandProvider.getInstance().getCommand(CommandName.EDIT_ALBUM_PAGE.name());
//            command.execute(req, resp);
            DispatchAssistant.redirectToCommand(req, resp, CommandName.ALBUMS_PAGE, message);
        } catch (ServiceException| ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        //крч норм выводит надо брать массив преобразовывать в айдишник дропать таблицу связей с эти айдишником альбома и фгарить новые

    }
}
