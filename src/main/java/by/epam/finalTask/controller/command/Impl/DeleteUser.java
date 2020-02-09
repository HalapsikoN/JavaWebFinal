package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.*;
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

public class DeleteUser implements Command {

    private final static Logger logger = LogManager.getLogger(DeleteUser.class);

    private final static UserService userService = ServiceFactory.getInstance().getUserService();

    private final static String SUCCESS_MSG="locale.deleteUser.successMsg";
    private final static String ERROR_MSG="locale.deleteUser.errorMsg";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            int userId=Integer.valueOf(req.getParameter(RequestParameterName.USER_ID));

            boolean isDeleted=userService.deleteUser(userId);
            String message;
            Locale locale= new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
            if(isDeleted){
                //req.setAttribute(RequestAttributeName.MESSAGE, SUCCESSFUL_MSG);
                message=ResourceManager.getString(SUCCESS_MSG, locale);
            }else {
               // req.setAttribute(RequestAttributeName.MESSAGE, FAILED_MSG);
                message=ResourceManager.getString(ERROR_MSG, locale);
            }

            int id=(int) session.getAttribute(SessionAttributeName.ID);

            if(id==userId && isDeleted){
                Command command= CommandProvider.getInstance().getCommand(CommandName.SIGN_OUT.name());
                command.execute(req, resp);
            }else {
//                Command command = CommandProvider.getInstance().getCommand(CommandName.USER_LIST.name());
//                command.execute(req, resp);
                DispatchAssistant.redirectToCommand(req, resp, CommandName.USER_LIST, message);
            }
        } catch (ServiceException| ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }
}
