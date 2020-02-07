package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.util.DispatchAssistant;
import by.epam.finalTask.controller.util.JspPageName;
import by.epam.finalTask.controller.util.RequestAttributeName;
import by.epam.finalTask.entity.Credit;
import by.epam.finalTask.entity.User;
import by.epam.finalTask.service.CreditService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import com.mysql.cj.log.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserBanListPage implements Command {

    private static final Logger logger= LogManager.getLogger(UserBanListPage.class);

    private static final CreditService creditService= ServiceFactory.getInstance().getCreditService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {
            List<User> userList=creditService.getAllBannedUsers();

            req.setAttribute(RequestAttributeName.USER_LIST, userList);

            DispatchAssistant.forwardToJsp(req, resp, JspPageName.BAN_USER_PAGE);
        } catch (ServiceException | ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }
    }
}
