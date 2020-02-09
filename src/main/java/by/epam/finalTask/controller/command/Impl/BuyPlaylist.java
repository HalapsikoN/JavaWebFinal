package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.entity.Bonus;
import by.epam.finalTask.entity.Playlist;
import by.epam.finalTask.entity.Track;
import by.epam.finalTask.entity.logic.TrackLogic;
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

public class BuyPlaylist implements Command {

    private final static Logger logger= LogManager.getLogger(BuyAlbum.class);

    private final static UserService userService= ServiceFactory.getInstance().getUserService();
    private final static BonusService bonusService=ServiceFactory.getInstance().getBonusService();
    private final static PlaylistService playlistService =ServiceFactory.getInstance().getPlaylistService();

    private final static String SUCCESS_MSG1="locale.buyPlaylist.successMsg1";
    private final static String SUCCESS_MSG2="locale.general.successMsg2";
    private final static String SUCCESS_MSG3="locale.general.successMsg3";
    private final static String ERROR_MSG="locale.buyPlaylist.errorMsg";
    private final static String MONEY_MSG ="locale.general.moneyMsg";
    private final static String ALREADY_HAVE ="locale.buyPlaylist.alreadyHave";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            int userId = (int) session.getAttribute(SessionAttributeName.ID);
            double wallet=(double) session.getAttribute(SessionAttributeName.WALLET);
            int playlistId=Integer.valueOf(req.getParameter(RequestParameterName.PLAYLIST_ID));

            List<Track> userTrackList=userService.getUserTracks(userId);

            Playlist playlist= playlistService.getPlaylist(playlistId);

            List<Track> playlistTracks=playlist.getTrackList();

            playlistTracks.removeAll(userTrackList);
            Locale locale= new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
            String message;
            if(playlistTracks.isEmpty()){
               // req.setAttribute(RequestAttributeName.MESSAGE, ALREADY_HAVE);
                message=ResourceManager.getString(ALREADY_HAVE, locale);
                req.setAttribute(RequestAttributeName.MESSAGE, message);
            }else{

                double price= TrackLogic.getTrackListPrice(playlistTracks);

                Bonus bonus = bonusService.getMaxValuableUserBonus(userId);
                double discountAmount = 0;
                if (bonus != null) {
                    discountAmount = price * bonus.getDiscount() * 0.01;
                    price -= discountAmount;
                }

                if (wallet > price) {
                    wallet -= price;
                    boolean isAddedPlaylist=userService.addPlaylistToUser(userId, playlistId);
                    boolean isAddedTracks=true;
                    for(Track track:playlistTracks){
                        if(!userService.addTrackToUser(userId, track.getId())){
                            isAddedTracks=false;
                        }
                    }
                    boolean isWalletUpdated=userService.updateUserWallet(userId, wallet);
                    if (isAddedPlaylist && isAddedTracks && isWalletUpdated) {
                        session.setAttribute(SessionAttributeName.WALLET, wallet);
                        message=ResourceManager.getString(SUCCESS_MSG1 + price + SUCCESS_MSG2 + discountAmount + SUCCESS_MSG3, locale);
                        req.setAttribute(RequestAttributeName.MESSAGE, message);
                        //req.setAttribute(RequestAttributeName.MESSAGE, SUCCESS_MSG1 + price + SUCCESS_MSG2 + discountAmount + SUCCESS_MSG3);
                    } else {
                        //req.setAttribute(RequestAttributeName.MESSAGE, ERROR_MSG);
                        message=ResourceManager.getString(ERROR_MSG, locale);
                        req.setAttribute(RequestAttributeName.MESSAGE, message);
                    }
                } else {
                    //req.setAttribute(RequestAttributeName.MESSAGE, MONEY_MSG);
                    message=ResourceManager.getString(MONEY_MSG, locale);
                    req.setAttribute(RequestAttributeName.MESSAGE, message);
                }

            }

            Command command= CommandProvider.getInstance().getCommand(CommandName.PLAYLIST_INFO.name());
            command.execute(req, resp);
          //  DispatchAssistant.redirectToCommand(req, resp, CommandName.PLAYLIST_INFO, message);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

}
