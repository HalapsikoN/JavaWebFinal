package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.*;
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
import java.util.Locale;

public class ChangeRole implements Command {

    private final static Logger logger = LogManager.getLogger(ChangeRole.class);

    private final static UserService userService = ServiceFactory.getInstance().getUserService();

    private final static String SUCCESS_MSG = "locale.changeRole.successMsg";
    private final static String ERROR_MSG = "locale.changeRole.errorMsg";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            int userId = RequestDataExecutor.getIntegerByName(RequestParameterName.USER_ID, req);

            Role newRole = Role.valueOf(req.getParameter(RequestParameterName.ROLE));

            boolean isChanged = userService.updateUserRole(userId, newRole);
            Locale locale = new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
            String message;
            if (isChanged) {
                message = ResourceManager.getString(SUCCESS_MSG, locale);
            } else {
                message = ResourceManager.getString(ERROR_MSG, locale);
            }

            int id = (int) session.getAttribute(SessionAttributeName.ID);

            if (id == userId && newRole == Role.USER && isChanged) {
                Command command = CommandProvider.getInstance().getCommand(CommandName.SIGN_OUT.name());
                command.execute(req, resp);
            } else {
                DispatchAssistant.redirectToCommand(req, resp, CommandName.USER_LIST, message);
            }
        } catch (ServiceException | ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }
    }
}
