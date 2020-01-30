package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
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
import java.io.IOException;

public class Registration implements Command {

    private static final Logger logger = LogManager.getLogger(Registration.class);

    private static final String SUCCESS_REGISTRATION ="Successfully added new user!";
    private static final String LOGIN_TAKEN="Email is already taken!";
    private static final String USER_STANDART_ROLE="USER";

    private static final UserService userService=ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        User user=createUserFromRequest(req);

        System.out.println(user);

        String password=RequestDataExecutor.getStringByName(RequestParameterName.PASSWORD, req);
        if(password==null){
            throw new CommandException("Invalid registration request");
        }

        boolean isSuccess=true;

        try {
            isSuccess=userService.register(user, password);

            System.out.println(isSuccess);

            if(isSuccess){
                req.setAttribute(RequestAttributeName.MESSAGE, SUCCESS_REGISTRATION);
            }else{
                req.setAttribute(RequestAttributeName.MESSAGE, LOGIN_TAKEN);
            }

            DispatchAssistant.forwardToJsp(req, resp, JspPageName.REGISTRATION_PAGE);
        } catch (ServiceException | ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }

    private User createUserFromRequest(HttpServletRequest req) throws CommandException{
        String name;
        String login;

        name= RequestDataExecutor.getStringByName(RequestParameterName.USER_NAME, req);
        login=RequestDataExecutor.getStringByName(RequestParameterName.LOGIN, req);

        if((login==null)||(name==null)){
            throw new CommandException("Invalid registration request");
        }

        return new User(name, login, Role.valueOf(USER_STANDART_ROLE));
    }
}
