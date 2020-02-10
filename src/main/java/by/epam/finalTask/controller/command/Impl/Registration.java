package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.entity.User;
import by.epam.finalTask.entity.util.Role;
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

public class Registration implements Command {

    private static final Logger logger = LogManager.getLogger(Registration.class);

    private static final String SUCCESS_REGISTRATION = "locale.registration.successMsg";
    private static final String LOGIN_TAKEN = "locale.registration.takenLogin";
    private static final String USER_STANDART_ROLE = "USER";

    private static final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        HttpSession session = SessionHelper.getExistingSession(req);

        if (session == null) {
            throw new CommandException("no session");
        }

        User user = createUserFromRequest(req);

        String password = null;
        try {
            password = RequestDataExecutor.getStringWithWriteEncoding(req, RequestParameterName.PASSWORD);
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        boolean isSuccess = true;
        String message;
        Locale locale = new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
        try {
            isSuccess = userService.register(user, password);

            if (isSuccess) {
                message = ResourceManager.getString(SUCCESS_REGISTRATION, locale);
            } else {
                message = ResourceManager.getString(LOGIN_TAKEN, locale);
            }

            DispatchAssistant.redirectToCommand(req, resp, CommandName.MAIN_PAGE, message);
        } catch (ServiceException | ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }

    private User createUserFromRequest(HttpServletRequest req) throws CommandException {
        String name;
        String login;

        try {
            name = RequestDataExecutor.getStringWithWriteEncoding(req, RequestParameterName.USER_NAME);
            login = RequestDataExecutor.getStringWithWriteEncoding(req, RequestParameterName.LOGIN);
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        return new User(name, login, Role.valueOf(USER_STANDART_ROLE));
    }
}
