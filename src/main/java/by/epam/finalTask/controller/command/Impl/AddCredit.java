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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AddCredit implements Command {

    private static final Logger logger= LogManager.getLogger(AddCredit.class);

    private static final UserService userService= ServiceFactory.getInstance().getUserService();
    private static final CreditService creditService=ServiceFactory.getInstance().getCreditService();

    private static final SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    private static final String SUCCESS_MSG="locale.addCredit.successMsg";
    private static final String ERROR_MSG="locale.addCredit.errorMsg";
    private static final String ALREADY_HAVE="locale.addCredit.alreadyHave";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            int userId = (int) session.getAttribute(SessionAttributeName.ID);
            double userWallet=(double) session.getAttribute(SessionAttributeName.WALLET);

            Credit actualCredit=creditService.getActualCredit(userId);
            Locale locale= new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
            String message;
            if(actualCredit==null) {
                double amount = RequestDataExecutor.getDoubleByName(RequestParameterName.AMOUNT, req);
                GregorianCalendar date = new GregorianCalendar();
                Date date_end = format.parse(req.getParameter(RequestParameterName.DATE));
                date.setTimeInMillis(date_end.getTime());

                Credit credit = new Credit(amount, date, userId);

                boolean isAdded=creditService.addCredit(credit);

                if(isAdded) {
                    double updatedWallet = userWallet + amount;

                    boolean isUpdated = userService.updateUserWallet(userId, updatedWallet);

                    if (isUpdated) {
                        session.setAttribute(SessionAttributeName.WALLET, updatedWallet);
                        //req.setAttribute(RequestAttributeName.MESSAGE, SUCCESSFULLY_UPDATE);
                        message=ResourceManager.getString(SUCCESS_MSG, locale);
                    } else {
                        //req.setAttribute(RequestAttributeName.MESSAGE, ERROR_MSG);
                        message=ResourceManager.getString(ERROR_MSG, locale);
                    }
                }else {
                    //req.setAttribute(RequestAttributeName.MESSAGE, ERROR_MSG);
                    message=ResourceManager.getString(ERROR_MSG, locale);
                }
            }else {
                //req.setAttribute(RequestAttributeName.MESSAGE, ALREADY_HAVE);
                message=ResourceManager.getString(ALREADY_HAVE, locale);
            }

//            Command command = CommandProvider.getInstance().getCommand(CommandName.USER_WALLET.name());
//            command.execute(req, resp);

            DispatchAssistant.redirectToCommand(req, resp, CommandName.USER_WALLET, message);
        } catch (ServiceException | ParseException| ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }
    }

}
