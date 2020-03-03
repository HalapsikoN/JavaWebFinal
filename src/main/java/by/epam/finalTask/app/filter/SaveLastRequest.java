package by.epam.finalTask.app.filter;

import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.controller.util.SessionAttributeName;
import by.epam.finalTask.controller.util.SessionHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveLastRequest implements Filter {

    private static final String LOCALE_REQUEST = "?locale=";
    private static final String QUESTION_MARK = "?";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpSession session = SessionHelper.createOrGetSession(request);

        StringBuffer requestURL = request.getRequestURL();

        if (!requestURL.toString().contains(LOCALE_REQUEST) && (request.getParameter(RequestParameterName.COMMAND_NAME)!=null && !request.getParameter(RequestParameterName.COMMAND_NAME).toUpperCase().equals(CommandName.GET_TRACK.name()))) {

            session.setAttribute(SessionAttributeName.LAST_URL, requestURL.toString() + QUESTION_MARK + request.getQueryString());

        }

        filterChain.doFilter(request, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
