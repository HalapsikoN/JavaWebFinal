package by.epam.finalTask.app.filter;

import by.epam.finalTask.controller.util.SessionAttributeName;
import by.epam.finalTask.controller.util.SessionHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveLastRequest implements Filter {

    private static final String LOCALE_REQUEST = "?locale=";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpSession session = SessionHelper.createOrGetSession(request);

        StringBuffer requestURL = request.getRequestURL();

        if(!requestURL.toString().contains(LOCALE_REQUEST)){

            session.setAttribute(SessionAttributeName.LAST_URL, requestURL.toString());

        }

        filterChain.doFilter(request, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
