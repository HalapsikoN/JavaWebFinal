package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.entity.User;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import by.epam.finalTask.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn implements Command {

    private static final Logger logger = LogManager.getLogger(SignIn.class);

    private static final UserService userService = ServiceFactory.getInstance().getUserService();

    private static final String INVALID_DATA="Invalid password or username";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        String login = RequestDataExecutor.getStringByName(RequestParameterName.LOGIN, req);
        String password = RequestDataExecutor.getStringByName(RequestParameterName.PASSWORD, req);

        if ((login == null) || (password == null)) {
            throw new CommandException("Invalid request");
        }

        User user = null;
        try {
            user = userService.signIn(login, password);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        Command command;
        if (user == null) {
            req.setAttribute(RequestAttributeName.MESSAGE, INVALID_DATA);
            command = CommandProvider.getInstance().getCommand(CommandName.SIGN_IN_PAGE.name());
        }else {
            HttpSession session = SessionHelper.createOrGetSession(req);
            SessionHelper.saveUserToSession(session, user);
            command = CommandProvider.getInstance().getCommand(CommandName.MAIN_PAGE.name());
        }

        command.execute(req, resp);
    }
}
