package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.RequestAttributeName;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.controller.util.SessionAttributeName;
import by.epam.finalTask.controller.util.SessionHelper;
import by.epam.finalTask.entity.util.Role;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import by.epam.finalTask.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeRole implements Command {

    private final static Logger logger = LogManager.getLogger(ChangeRole.class);

    private final static UserService userService = ServiceFactory.getInstance().getUserService();

    private final static String SUCCESSFUL_MSG="Role has been updated!";
    private final static String FAILED_MSG="Role has not been updated!";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            int userId=Integer.valueOf(req.getParameter(RequestParameterName.USER_ID));

            Role newRole=Role.valueOf(req.getParameter(RequestParameterName.ROLE));

            boolean isChanged=userService.updateUserRole(userId, newRole);

            if(isChanged){
                req.setAttribute(RequestAttributeName.MESSAGE, SUCCESSFUL_MSG);
            }else {
                req.setAttribute(RequestAttributeName.MESSAGE, FAILED_MSG);
            }

            HttpSession session= SessionHelper.getExistingSession(req);
            int id=(int) session.getAttribute(SessionAttributeName.ID);

            if(id==userId && newRole==Role.USER && isChanged){
                Command command= CommandProvider.getInstance().getCommand(CommandName.SIGN_OUT.name());
                command.execute(req, resp);
            }else {
                Command command = CommandProvider.getInstance().getCommand(CommandName.EDIT_USER_PAGE.name());
                command.execute(req, resp);
            }
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
    }
}
