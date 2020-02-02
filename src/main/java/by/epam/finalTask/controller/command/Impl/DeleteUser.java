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

public class DeleteUser implements Command {

    private final static Logger logger = LogManager.getLogger(DeleteUser.class);

    private final static UserService userService = ServiceFactory.getInstance().getUserService();

    private final static String SUCCESSFUL_MSG="User has been deleted!";
    private final static String FAILED_MSG="User has not been deleted!";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            int userId=Integer.valueOf(req.getParameter(RequestParameterName.USER_ID));

            boolean isDeleted=userService.deleteUser(userId);

            if(isDeleted){
                req.setAttribute(RequestAttributeName.MESSAGE, SUCCESSFUL_MSG);
            }else {
                req.setAttribute(RequestAttributeName.MESSAGE, FAILED_MSG);
            }

            HttpSession session= SessionHelper.getExistingSession(req);
            int id=(int) session.getAttribute(SessionAttributeName.ID);

            if(id==userId && isDeleted){
                Command command= CommandProvider.getInstance().getCommand(CommandName.SIGN_OUT.name());
                command.execute(req, resp);
            }else {
                Command command = CommandProvider.getInstance().getCommand(CommandName.USER_LIST.name());
                command.execute(req, resp);
            }
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }
}
