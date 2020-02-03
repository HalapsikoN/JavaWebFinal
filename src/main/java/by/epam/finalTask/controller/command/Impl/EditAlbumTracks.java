package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.RequestAttributeName;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.service.AlbumService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditAlbumTracks implements Command {

    private static final Logger logger= LogManager.getLogger(EditAlbumTracks.class);

    private static final AlbumService albumService= ServiceFactory.getInstance().getAlbumService();

    private static final String SUCCESS_MSG="Album tracks has been updated!";
    private static final String ERROR_MSG="Album tracks has not been updated!";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {
            int albumId = Integer.valueOf(req.getParameter(RequestParameterName.ALBUM_ID));

            String[] tracksUpdate = req.getParameterValues(RequestParameterName.TRACKS_UPDATE);

            List<Integer> trackIdList = new ArrayList<>();
            for (String track : tracksUpdate) {
                trackIdList.add(Integer.valueOf(track));
            }

            boolean isUpdated = albumService.updateAlbumTracks(albumId, trackIdList);

            if (isUpdated) {
                req.setAttribute(RequestAttributeName.MESSAGE, SUCCESS_MSG);
            } else {
                req.setAttribute(RequestAttributeName.MESSAGE, ERROR_MSG);
            }
            Command command = CommandProvider.getInstance().getCommand(CommandName.EDIT_ALBUM_PAGE.name());
            command.execute(req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        //крч норм выводит надо брать массив преобразовывать в айдишник дропать таблицу связей с эти айдишником альбома и фгарить новые

    }
}
