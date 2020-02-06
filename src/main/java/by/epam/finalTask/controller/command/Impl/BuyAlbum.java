package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.RequestAttributeName;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.controller.util.SessionAttributeName;
import by.epam.finalTask.controller.util.SessionHelper;
import by.epam.finalTask.entity.Album;
import by.epam.finalTask.entity.Bonus;
import by.epam.finalTask.entity.Track;
import by.epam.finalTask.entity.logic.AlbumLogic;
import by.epam.finalTask.entity.logic.TrackLogic;
import by.epam.finalTask.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BuyAlbum implements Command {

    private final static Logger logger= LogManager.getLogger(BuyAlbum.class);

    private final static UserService userService= ServiceFactory.getInstance().getUserService();
    private final static BonusService bonusService=ServiceFactory.getInstance().getBonusService();
    private final static AlbumService albumService=ServiceFactory.getInstance().getAlbumService();

    private final static String SUCCESS_MSG1="Album has been bought for (";
    private final static String SUCCESS_MSG2=" $) with discount amount (";
    private final static String SUCCESS_MSG3="$)";
    private final static String ERROR_MSG="Album has not been bought";
    private final static String MONEY_MSG ="Not enough money.";
    private final static String ALREADY_HAVE ="You have already had all tracks from album.";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            int userId = (int) session.getAttribute(SessionAttributeName.ID);
            double wallet=(double) session.getAttribute(SessionAttributeName.WALLET);
            int albumId=Integer.valueOf(req.getParameter(RequestParameterName.ALBUM_ID));

            List<Track> userTrackList=userService.getUserTracks(userId);

            Album album=albumService.getAlbum(albumId);

            List<Track> albumTracks=album.getTrackList();

            albumTracks.removeAll(userTrackList);

            if(albumTracks.isEmpty()){
                req.setAttribute(RequestAttributeName.MESSAGE, ALREADY_HAVE);
            }else{

                double price= TrackLogic.getTrackListPrice(albumTracks);

                Bonus bonus = bonusService.getMaxValuableUserBonus(userId);
                double discountAmount = 0;
                if (bonus != null) {
                    discountAmount = price * bonus.getDiscount() * 0.01;
                    price -= discountAmount;
                }

                if (wallet > price) {
                    wallet -= price;
                    boolean isAddedAlbum=userService.addAlbumToUser(userId, albumId);
                    boolean isAddedTracks=true;
                    for(Track track:albumTracks){
                        if(!userService.addTrackToUser(userId, track.getId())){
                            isAddedTracks=false;
                        }
                    }
                    boolean isWalletUpdated=userService.updateUserWallet(userId, wallet);
                    if (isAddedAlbum && isAddedTracks && isWalletUpdated) {
                        session.setAttribute(SessionAttributeName.WALLET, wallet);
                        req.setAttribute(RequestAttributeName.MESSAGE, SUCCESS_MSG1 + price + SUCCESS_MSG2 + discountAmount + SUCCESS_MSG3);
                    } else {
                        req.setAttribute(RequestAttributeName.MESSAGE, ERROR_MSG);
                    }
                } else {
                    req.setAttribute(RequestAttributeName.MESSAGE, MONEY_MSG);
                }

            }

            Command command= CommandProvider.getInstance().getCommand(CommandName.ALBUM_INFO.name());
            command.execute(req, resp);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
