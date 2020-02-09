package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.entity.User;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import by.epam.finalTask.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class SignIn implements Command {

    private static final Logger logger = LogManager.getLogger(SignIn.class);

    private static final UserService userService = ServiceFactory.getInstance().getUserService();

    private static final String INVALID_DATA="locale.signIn.invalidData";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        HttpSession session = SessionHelper.getExistingSession(req);

        if (session == null) {
            throw new CommandException("no session");
        }

        String login = null;
        String password = null;
        try {
            login=RequestDataExecutor.getStringWithWriteEncoding(req, RequestParameterName.LOGIN);
            password = RequestDataExecutor.getStringWithWriteEncoding(req, RequestParameterName.PASSWORD);
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        User user = null;
        try {
            user = userService.signIn(login, password);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        String message;
        Locale locale= new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
        try {
            //Command command;
            if (user == null) {
                //req.setAttribute(RequestAttributeName.MESSAGE, INVALID_DATA);
                message=ResourceManager.getString(INVALID_DATA, locale);
                //command = CommandProvider.getInstance().getCommand(CommandName.SIGN_IN_PAGE.name());
                DispatchAssistant.redirectToCommand(req, resp, CommandName.SIGN_IN_PAGE, message);
            } else {
                SessionHelper.saveUserToSession(session, user);
                //command = CommandProvider.getInstance().getCommand(CommandName.MAIN_PAGE.name());
                DispatchAssistant.redirectToCommand(req, resp, CommandName.MAIN_PAGE);
            }
        } catch (ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        // command.execute(req, resp);
    }
}
