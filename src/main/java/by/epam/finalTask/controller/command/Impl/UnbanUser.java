package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.util.DispatchAssistant;
import by.epam.finalTask.controller.util.RequestDataExecutor;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.service.CreditService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnbanUser implements Command {

    private final static Logger logger = LogManager.getLogger(UnbanUser.class);

    private final static CreditService creditService = ServiceFactory.getInstance().getCreditService();


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            int userId = RequestDataExecutor.getIntegerByName(RequestParameterName.USER_ID, req);

            creditService.unbanUser(userId);

            DispatchAssistant.redirectToCommand(req, resp, CommandName.USER_BAN_LIST);
        } catch (ServiceException | ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }
}
