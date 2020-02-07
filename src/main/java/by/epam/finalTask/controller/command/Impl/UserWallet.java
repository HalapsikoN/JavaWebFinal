package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.entity.Bonus;
import by.epam.finalTask.entity.Credit;
import by.epam.finalTask.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserWallet implements Command {

    private final static Logger logger= LogManager.getLogger(UserWallet.class);

    private final static UserService userService= ServiceFactory.getInstance().getUserService();
    private final static BonusService bonusService=ServiceFactory.getInstance().getBonusService();
    private final static CreditService creditService=ServiceFactory.getInstance().getCreditService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {

            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            int userId = (int) session.getAttribute(SessionAttributeName.ID);

            List<Bonus> bonusList =bonusService.getUserBonuses(userId);

            req.setAttribute(RequestAttributeName.BONUS_LIST, bonusList);

            Credit credit=creditService.getUserCredit(userId);

            req.setAttribute(RequestAttributeName.CREDIT, credit);

            DispatchAssistant.forwardToJsp(req, resp, JspPageName.USER_WALLET);
        } catch (ServiceException | ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }
    }
}
