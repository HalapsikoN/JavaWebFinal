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
import java.util.Locale;

public class UpdateUserWallet implements Command {

    private static final Logger logger = LogManager.getLogger(UpdateUserWallet.class);

    private static final UserService userService = ServiceFactory.getInstance().getUserService();
    private static final CreditService creditService = ServiceFactory.getInstance().getCreditService();

    private static final String SUCCESS_MSG = "locale.update.successMsg";
    private static final String ERROR_MSG = "locale.update.errorMsg";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            int userId = (int) session.getAttribute(SessionAttributeName.ID);
            double userWallet = (double) session.getAttribute(SessionAttributeName.WALLET);

            double addToWallet = RequestDataExecutor.getDoubleByName(RequestParameterName.AMOUNT, req);
            String message;
            Locale locale= new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
            Credit credit = creditService.getUserCredit(userId);
            if (credit != null) {
                double creditAmountToPay = credit.getAmount() - addToWallet;

                if (creditAmountToPay > 0) {
                    creditService.updateCreditAmount(credit.getId(), creditAmountToPay);
                    addToWallet=0;
                } else {
                    creditService.deleteCredit(credit.getId());
                    addToWallet-=credit.getAmount();
                }
            }
            double updatedWallet = userWallet + addToWallet;

            boolean isUpdated = userService.updateUserWallet(userId, updatedWallet);

            if (isUpdated) {
                session.setAttribute(SessionAttributeName.WALLET, updatedWallet);
                //req.setAttribute(RequestAttributeName.MESSAGE, SUCCESSFULLY_UPDATE);
                message=ResourceManager.getString(SUCCESS_MSG, locale);

            } else {
                //req.setAttribute(RequestAttributeName.MESSAGE, ERROR_MSG);
                message=ResourceManager.getString(ERROR_MSG, locale);
            }

            DispatchAssistant.redirectToCommand(req, resp, CommandName.USER_WALLET, message);
//            Command command = CommandProvider.getInstance().getCommand(CommandName.USER_WALLET.name());
//            command.execute(req, resp);
        } catch (ServiceException|ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }
    }
}
