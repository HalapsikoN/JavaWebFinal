package by.epam.finalTask.app.filter;

import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.controller.util.SessionAttributeName;
import by.epam.finalTask.controller.util.SessionHelper;
import by.epam.finalTask.entity.util.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class CommandFilter implements Filter {

    private Set<String> userCommand= new HashSet<>();
    private Set<String> adminCommand= new HashSet<>();

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


        adminCommand.add(CommandName.USER_PROFILE.name());
        adminCommand.add(CommandName.UPDATE_USERNAME.name());
        adminCommand.add(CommandName.UPDATE_PASSWORD.name());
        adminCommand.add(CommandName.SIGN_OUT.name());
        adminCommand.add(CommandName.USER_LIST.name());
        adminCommand.add(CommandName.EDIT_USER_PAGE.name());
        adminCommand.add(CommandName.CHANGE_ROLE.name());
        adminCommand.add(CommandName.DELETE_USER.name());
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
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        HttpSession session= SessionHelper.getExistingSession(request);

        String command=request.getParameter(RequestParameterName.COMMAND_NAME);

        if ((command == null) || (!userCommand.contains(command.toUpperCase())) || (!adminCommand.contains(command.toUpperCase()))) {
            filterChain.doFilter(request, response);
            return;
        }else {
            command=command.toUpperCase();
        }

        System.out.println("filter:"+command);

        if(session!=null && Role.valueOf((String) session.getAttribute(SessionAttributeName.ROLE))==Role.USER && userCommand.contains(command)){
            System.out.println("tyt1");
            filterChain.doFilter(request, response);
            return;
        }

        if(session!=null && Role.valueOf((String) session.getAttribute(SessionAttributeName.ROLE))==Role.ADMIN && adminCommand.contains(command)){
            System.out.println("tyt2");
            filterChain.doFilter(request, response);
            return;
        }

        if((session==null || isGuest(session)) && (adminCommand.contains(command) || userCommand.contains(command))){
            System.out.println("tyt3");
            response.sendRedirect(request.getContextPath());
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
