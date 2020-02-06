package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.entity.Credit;
import by.epam.finalTask.service.CreditService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import by.epam.finalTask.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddCredit implements Command {

    private static final Logger logger= LogManager.getLogger(AddCredit.class);

    private static final UserService userService= ServiceFactory.getInstance().getUserService();
    private static final CreditService creditService=ServiceFactory.getInstance().getCreditService();

    private static final SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    private static final String SUCCESSFULLY_UPDATE="Successfully added";
    private static final String ERROR_MSG="Problems at adding";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            int userId = (int) session.getAttribute(SessionAttributeName.ID);
            double userWallet=(double) session.getAttribute(SessionAttributeName.WALLET);

            //проверить есть ли у юзера кредит

            double amount=Double.valueOf(req.getParameter(RequestParameterName.AMOUNT));
            GregorianCalendar date=new GregorianCalendar();
            Date date_end=format.parse(req.getParameter(RequestParameterName.DATE));
            date.setTimeInMillis(date_end.getTime());

            Credit credit=new Credit(amount, date, userId);

            //добавил кредит

            double updatedWallet=userWallet+amount;

            boolean isUpdated=userService.updateUserWallet(userId, updatedWallet);

            if(isUpdated){
                session.setAttribute(SessionAttributeName.WALLET, updatedWallet);
                req.setAttribute(RequestAttributeName.MESSAGE, SUCCESSFULLY_UPDATE);
            }else {
                req.setAttribute(RequestAttributeName.MESSAGE, ERROR_MSG);
            }

            Command command = CommandProvider.getInstance().getCommand(CommandName.USER_WALLET.name());
            command.execute(req, resp);
        } catch (ServiceException | ParseException e) {
            logger.error(e);
            throw new CommandException(e);
        }
    }

}
