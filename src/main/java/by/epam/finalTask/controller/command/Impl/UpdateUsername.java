package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.RequestAttributeName;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.controller.util.SessionAttributeName;
import by.epam.finalTask.controller.util.SessionHelper;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import by.epam.finalTask.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUsername implements Command {

    private static final Logger logger= LogManager.getLogger(UpdateUsername.class);

    private static final UserService userService= ServiceFactory.getInstance().getUserService();

    private static final String SUCCESSFULLY_UPDATE="Successfully updated";
    private static final String ERROR_MSG="Problems at update";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {
            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            int userId = (int) session.getAttribute(SessionAttributeName.ID);

            String newUsername=req.getParameter(RequestParameterName.NEW_USERNAME);

            boolean isUpdated=userService.updateUserUsername(userId, newUsername);

            if(isUpdated){
                session.setAttribute(SessionAttributeName.USERNAME, newUsername);
                req.setAttribute(RequestAttributeName.MESSAGE, SUCCESSFULLY_UPDATE);
            }else {
                req.setAttribute(RequestAttributeName.MESSAGE, ERROR_MSG);
            }

            Command command = CommandProvider.getInstance().getCommand(CommandName.USER_PROFILE.name());
            command.execute(req, resp);
        }catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
    }
}
