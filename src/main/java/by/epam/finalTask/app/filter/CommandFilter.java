package by.epam.finalTask.app.filter;

import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.controller.util.SessionAttributeName;
import by.epam.finalTask.controller.util.SessionHelper;
import by.epam.finalTask.entity.util.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class CommandFilter implements Filter {

    private final String REDIRECT_PATH = "/atrack";

    private Set<String> userCommand = new HashSet<>();
    private Set<String> adminCommand = new HashSet<>();
    private Set<String> unregisterCommand = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userCommand.add(CommandName.USER_TRACKS.name());
        userCommand.add(CommandName.USER_ALBUMS.name());
        userCommand.add(CommandName.USER_PLAYLISTS.name());
        userCommand.add(CommandName.USER_WALLET.name());
        userCommand.add(CommandName.ADD_USER_WALLET.name());
        userCommand.add(CommandName.USER_PROFILE.name());
        userCommand.add(CommandName.UPDATE_USERNAME.name());
        userCommand.add(CommandName.UPDATE_PASSWORD.name());
        userCommand.add(CommandName.SIGN_OUT.name());
        userCommand.add(CommandName.ADD_COMMENT.name());
        userCommand.add(CommandName.BUY_TRACK.name());
        userCommand.add(CommandName.MAIN_PAGE.name());
        userCommand.add(CommandName.ALBUMS_PAGE.name());
        userCommand.add(CommandName.PLAYLISTS_PAGE.name());
        userCommand.add(CommandName.ALBUM_INFO.name());
        userCommand.add(CommandName.PLAYLIST_INFO.name());
        userCommand.add(CommandName.BUY_ALBUM.name());
        userCommand.add(CommandName.BUY_PLAYLIST.name());
        userCommand.add(CommandName.ADD_CREDIT.name());
        userCommand.add(CommandName.BAN_PAGE.name());
        userCommand.add(CommandName.GET_TRACK.name());


        adminCommand.add(CommandName.USER_PROFILE.name());
        adminCommand.add(CommandName.UPDATE_USERNAME.name());
        adminCommand.add(CommandName.UPDATE_PASSWORD.name());
        adminCommand.add(CommandName.SIGN_OUT.name());
        adminCommand.add(CommandName.USER_LIST.name());
        adminCommand.add(CommandName.EDIT_USER_PAGE.name());
        adminCommand.add(CommandName.CHANGE_ROLE.name());
        adminCommand.add(CommandName.DELETE_USER.name());
        adminCommand.add(CommandName.ADD_TRACK.name());
        adminCommand.add(CommandName.ADD_TRACK_PAGE.name());
        adminCommand.add(CommandName.EDIT_TRACK_PAGE.name());
        adminCommand.add(CommandName.EDIT_TRACK.name());
        adminCommand.add(CommandName.DELETE_TRACK.name());
        adminCommand.add(CommandName.ADD_ALBUM_PAGE.name());
        adminCommand.add(CommandName.ADD_ALBUM.name());
        adminCommand.add(CommandName.EDIT_ALBUM_PAGE.name());
        adminCommand.add(CommandName.EDIT_ALBUM_TRACKS.name());
        adminCommand.add(CommandName.EDIT_ALBUM.name());
        adminCommand.add(CommandName.DELETE_ALBUM.name());
        adminCommand.add(CommandName.ADD_PLAYLIST_PAGE.name());
        adminCommand.add(CommandName.EDIT_PLAYLIST_PAGE.name());
        adminCommand.add(CommandName.ADD_PLAYLIST.name());
        adminCommand.add(CommandName.EDIT_PLAYLIST_TRACKS.name());
        adminCommand.add(CommandName.EDIT_PLAYLIST.name());
        adminCommand.add(CommandName.DELETE_PLAYLIST.name());
        adminCommand.add(CommandName.ADD_BONUS.name());
        adminCommand.add(CommandName.MAIN_PAGE.name());
        adminCommand.add(CommandName.ALBUMS_PAGE.name());
        adminCommand.add(CommandName.PLAYLISTS_PAGE.name());
        adminCommand.add(CommandName.ALBUM_INFO.name());
        adminCommand.add(CommandName.PLAYLIST_INFO.name());
        adminCommand.add(CommandName.USER_BAN_LIST.name());
        adminCommand.add(CommandName.UNBAN_USER.name());
        adminCommand.add(CommandName.GET_TRACK.name());

        unregisterCommand.add(CommandName.MAIN_PAGE.name());
        unregisterCommand.add(CommandName.ALBUMS_PAGE.name());
        unregisterCommand.add(CommandName.PLAYLISTS_PAGE.name());
        unregisterCommand.add(CommandName.PLAYLIST_INFO.name());
        unregisterCommand.add(CommandName.ALBUM_INFO.name());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = SessionHelper.getExistingSession(request);

        String command = request.getParameter(RequestParameterName.COMMAND_NAME);

        if ((command == null)) {
            filterChain.doFilter(request, response);
            return;
        } else {
            command = command.toUpperCase();
        }


        if (session != null && session.getAttribute(SessionAttributeName.ID) != null) {
            Role role = (Role) session.getAttribute(SessionAttributeName.ROLE);

            if (role == Role.USER && userCommand.contains(command)) {

                filterChain.doFilter(request, response);
                return;
            }

            if (role == Role.ADMIN && adminCommand.contains(command)) {

                filterChain.doFilter(request, response);
                return;
            }

            response.sendRedirect(request.getContextPath() + REDIRECT_PATH);
        } else {
            if ((adminCommand.contains(command) || userCommand.contains(command)) && (!unregisterCommand.contains(command))) {

                response.sendRedirect(request.getContextPath() + REDIRECT_PATH);
            } else {

                filterChain.doFilter(request, response);
            }
        }

    }

    @Override
    public void destroy() {

    }

    private boolean isGuest(HttpSession session) {
        return (session == null) ||
                (session.getAttribute(SessionAttributeName.ID) == null);
    }
}
