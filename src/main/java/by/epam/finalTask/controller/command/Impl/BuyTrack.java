package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.entity.Bonus;
import by.epam.finalTask.entity.Track;
import by.epam.finalTask.entity.User;
import by.epam.finalTask.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class BuyTrack implements Command {

    private final static Logger logger= LogManager.getLogger(BuyTrack.class);

    private final static UserService userService= ServiceFactory.getInstance().getUserService();
    private final static BonusService bonusService=ServiceFactory.getInstance().getBonusService();
    private final static TrackService trackService=ServiceFactory.getInstance().getTrackService();

    private final static String SUCCESS_MSG1="Track has been bought for (";
    private final static String SUCCESS_MSG2=" $) with discount amount (";
    private final static String SUCCESS_MSG3="$)";
    private final static String ERROR_MSG="Track has not been bought";
    private final static String MONEY_MSG ="Not enough money.";
    private final static String ALREADY_HAVE ="You have already had this track.";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {
            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            int userId = (int) session.getAttribute(SessionAttributeName.ID);
            double wallet=(double) session.getAttribute(SessionAttributeName.WALLET);
            int trackId = Integer.valueOf(req.getParameter(RequestParameterName.TRACK_ID));

            List<Track> trackList=userService.getUserTracks(userId);

            Track track=trackService.getTrack(trackId);

            if (trackList.contains(track)){
                req.setAttribute(RequestAttributeName.MESSAGE, ALREADY_HAVE);
            }else {
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
                        req.setAttribute(RequestAttributeName.MESSAGE, SUCCESS_MSG1 + price + SUCCESS_MSG2 + discountAmount + SUCCESS_MSG3);
                    } else {
                        req.setAttribute(RequestAttributeName.MESSAGE, ERROR_MSG);
                    }
                } else {
                    req.setAttribute(RequestAttributeName.MESSAGE, MONEY_MSG);
                }
            }

            Command command= CommandProvider.getInstance().getCommand(CommandName.MAIN_PAGE.name());
            command.execute(req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }
}
