package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.DispatchAssistant;
import by.epam.finalTask.controller.util.SessionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOut implements Command {

    private static final Logger logger= LogManager.getLogger(SignOut.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        HttpSession session=SessionHelper.getExistingSession(req);

        SessionHelper.deleteUserFromSession(session);

//        Command command= CommandProvider.getInstance().getCommand(CommandName.MAIN_PAGE.name());
//        command.execute(req,resp);
        try {
            DispatchAssistant.redirectToCommand(req, resp, CommandName.MAIN_PAGE);
        } catch (ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }
    }
}
