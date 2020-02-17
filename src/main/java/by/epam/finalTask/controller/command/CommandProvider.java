package by.epam.finalTask.controller.command;

import by.epam.finalTask.controller.command.Impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private static final Logger logger = LogManager.getLogger(CommandProvider.class);

    private final static CommandProvider instance = new CommandProvider();
    private final Map<CommandName, Command> repository = new HashMap<>();

    private final NoSuchCommand noSuchCommand = new NoSuchCommand();

    private CommandProvider() {
        repository.put(CommandName.REGISTRATION_PAGE, new RegistrationPage());
        repository.put(CommandName.REGISTRATION, new Registration());
        repository.put(CommandName.SIGN_IN_PAGE, new SignInPage());
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.MAIN_PAGE, new MainPage());
        repository.put(CommandName.ALBUMS_PAGE, new AlbumsPage());
        repository.put(CommandName.PLAYLISTS_PAGE, new PlaylistsPage());
        repository.put(CommandName.USER_TRACKS, new UserTracks());
        repository.put(CommandName.ALBUM_INFO, new AlbumInfo());
        repository.put(CommandName.USER_ALBUMS, new UserAlbums());
        repository.put(CommandName.PLAYLIST_INFO, new PlaylistInfo());
        repository.put(CommandName.USER_PLAYLISTS, new UserPlaylists());
        repository.put(CommandName.USER_WALLET, new UserWallet());
        repository.put(CommandName.ADD_USER_WALLET, new UpdateUserWallet());
        repository.put(CommandName.USER_PROFILE, new UserProfile());
        repository.put(CommandName.UPDATE_USERNAME, new UpdateUsername());
        repository.put(CommandName.UPDATE_PASSWORD, new UpdatePassword());
        repository.put(CommandName.SIGN_OUT, new SignOut());
        repository.put(CommandName.ADD_COMMENT, new AddComment());
        repository.put(CommandName.USER_LIST, new UserList());
        repository.put(CommandName.EDIT_USER_PAGE, new EditUserPage());
        repository.put(CommandName.CHANGE_ROLE, new ChangeRole());
        repository.put(CommandName.DELETE_USER, new DeleteUser());
        repository.put(CommandName.ADD_TRACK_PAGE, new AddTrackPage());
        repository.put(CommandName.ADD_TRACK, new AddTrack());
        repository.put(CommandName.EDIT_TRACK_PAGE, new EditTrackPage());
        repository.put(CommandName.EDIT_TRACK, new EditTrack());
        repository.put(CommandName.DELETE_TRACK, new DeleteTrack());
        repository.put(CommandName.ADD_ALBUM_PAGE, new AddAlbumPage());
        repository.put(CommandName.ADD_ALBUM, new AddAlbum());
        repository.put(CommandName.EDIT_ALBUM_PAGE, new EditAlbumPage());
        repository.put(CommandName.EDIT_ALBUM_TRACKS, new EditAlbumTracks());
        repository.put(CommandName.EDIT_ALBUM, new EditAlbum());
        repository.put(CommandName.DELETE_ALBUM, new DeleteAlbum());
        repository.put(CommandName.ADD_PLAYLIST_PAGE, new AddPlaylistPage());
        repository.put(CommandName.ADD_PLAYLIST, new AddPlaylist());
        repository.put(CommandName.EDIT_PLAYLIST_PAGE, new EditPlaylistPage());
        repository.put(CommandName.EDIT_PLAYLIST_TRACKS, new EditPlaylistTracks());
        repository.put(CommandName.EDIT_PLAYLIST, new EditPlaylist());
        repository.put(CommandName.DELETE_PLAYLIST, new DeletePlaylist());
        repository.put(CommandName.ADD_BONUS, new AddBonus());
        repository.put(CommandName.BUY_TRACK, new BuyTrack());
        repository.put(CommandName.BUY_ALBUM, new BuyAlbum());
        repository.put(CommandName.BUY_PLAYLIST, new BuyPlaylist());
        repository.put(CommandName.ADD_CREDIT, new AddCredit());
        repository.put(CommandName.BAN_PAGE, new BanPage());
        repository.put(CommandName.USER_BAN_LIST, new UserBanListPage());
        repository.put(CommandName.UNBAN_USER, new UnbanUser());
        repository.put(CommandName.GET_TRACK, new GetTrack());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String name) {
        CommandName commandName;
        Command command;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.warn("No such command: " + name);
            command = noSuchCommand;
        }

        return command;
    }
}
