package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class EditAlbumTracks implements Command {

    private static final Logger logger= LogManager.getLogger(EditAlbumTracks.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        for(String key:req.getParameterMap().keySet()){
            System.out.println(Arrays.toString(req.getParameterMap().get(key)));
        }

        //крч норм выводит надо брать массив преобразовывать в айдишник дропать таблицу связей с эти айдишником альбома и фгарить новые

    }
}
