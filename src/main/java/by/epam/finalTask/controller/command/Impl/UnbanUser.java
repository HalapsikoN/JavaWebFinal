package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.service.CreditService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnbanUser implements Command {

    private final static Logger logger= LogManager.getLogger(UnbanUser.class);

    private final static CreditService creditService= ServiceFactory.getInstance().getCreditService();


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            int userId=Integer.valueOf(req.getParameter(RequestParameterName.USER_ID));

            creditService.unbanUser(userId);

            Command command= CommandProvider.getInstance().getCommand(CommandName.USER_BAN_LIST.name());
            command.execute(req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }
}
