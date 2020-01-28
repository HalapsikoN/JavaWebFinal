package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.util.DispatchAssistant;
import by.epam.finalTask.controller.util.JspPageName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInPage implements Command {

    private static final Logger logger = LogManager.getLogger(SignInPage.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {
            DispatchAssistant.forwardToJsp(req, resp, JspPageName.SIGN_IN_PAGE);
        } catch (ServletException | IOException e) {
            logger.error(e);
            //что-то ещё
            throw new CommandException(e);
        }
    }
}
