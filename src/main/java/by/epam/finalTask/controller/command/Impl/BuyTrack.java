package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.entity.Bonus;
import by.epam.finalTask.entity.Track;
import by.epam.finalTask.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class BuyTrack implements Command {

    private final static Logger logger = LogManager.getLogger(BuyTrack.class);

    private final static UserService userService = ServiceFactory.getInstance().getUserService();
    private final static BonusService bonusService = ServiceFactory.getInstance().getBonusService();
    private final static TrackService trackService = ServiceFactory.getInstance().getTrackService();

    private final static String SUCCESS_MSG1 = "locale.buyTrack.successMsg1";
    private final static String SUCCESS_MSG2 = "locale.general.successMsg2";
    private final static String SUCCESS_MSG3 = "locale.general.successMsg3";
    private final static String ERROR_MSG = "locale.buyTrack.errorMsg";
    private final static String MONEY_MSG = "locale.general.moneyMsg";
    private final static String ALREADY_HAVE = "locale.buyTrack.alreadyHave";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {
            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            int userId = (int) session.getAttribute(SessionAttributeName.ID);
            double wallet = (double) session.getAttribute(SessionAttributeName.WALLET);
            int trackId = RequestDataExecutor.getIntegerByName(RequestParameterName.TRACK_ID, req);

            List<Track> trackList = userService.getUserTracks(userId);

            Track track = trackService.getTrack(trackId);
            Locale locale = new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
            String message;
            if (trackList.contains(track)) {
                message = ResourceManager.getString(ALREADY_HAVE, locale);
                req.setAttribute(RequestAttributeName.MESSAGE, message);
            } else {
                double price = track.getPrice();

                Bonus bonus = bonusService.getMaxValuableUserBonus(userId);
                double discountAmount = 0;
                if (bonus != null) {
                    discountAmount = price * bonus.getDiscount() * 0.01;
                    price -= discountAmount;
                }


                if (wallet > price) {
                    wallet -= price;
                    if (userService.addTrackToUser(userId, trackId) && userService.updateUserWallet(userId, wallet)) {
                        session.setAttribute(SessionAttributeName.WALLET, wallet);
                        message = ResourceManager.getString(SUCCESS_MSG1, locale) + price + ResourceManager.getString(SUCCESS_MSG2, locale) + discountAmount + ResourceManager.getString(SUCCESS_MSG3, locale);
                        req.setAttribute(RequestAttributeName.MESSAGE, message);
                    } else {
                        message = ResourceManager.getString(ERROR_MSG, locale);
                        req.setAttribute(RequestAttributeName.MESSAGE, message);
                    }
                } else {
                    message = ResourceManager.getString(MONEY_MSG, locale);
                    req.setAttribute(RequestAttributeName.MESSAGE, message);
                }

            }
            DispatchAssistant.redirectToCommand(req, resp, CommandName.MAIN_PAGE, message);
        } catch (ServiceException | ServletException | IOException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }
}
