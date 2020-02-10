package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
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
import java.io.IOException;

public class EditUserPage implements Command {

    private final static Logger logger = LogManager.getLogger(EditUserPage.class);

    private final static UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {
            int userId = RequestDataExecutor.getIntegerByName(RequestParameterName.USER_ID, req);

            User user = userService.getUser(userId);

            req.setAttribute(RequestAttributeName.USER, user);

            DispatchAssistant.forwardToJsp(req, resp, JspPageName.EDIT_USER_PAGE);
        } catch (ServletException | IOException | ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
    }
}
