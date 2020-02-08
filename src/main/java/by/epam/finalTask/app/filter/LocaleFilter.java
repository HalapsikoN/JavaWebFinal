package by.epam.finalTask.app.filter;

import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.controller.util.SessionAttributeName;
import by.epam.finalTask.controller.util.SessionHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocaleFilter implements Filter {

    private static final String DEFAULT_LANG = "en";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = SessionHelper.createOrGetSession(req);

        String lastUrl;
        String locale = req.getParameter(RequestParameterName.LOCALE);
        if (locale != null) {

            session.setAttribute(SessionAttributeName.LOCALE, locale);

            lastUrl = (String) session.getAttribute(SessionAttributeName.LAST_URL);
            if (lastUrl != null) {
                resp.sendRedirect(lastUrl);
                return;
            }

        } else {

            if (session.getAttribute(SessionAttributeName.LOCALE) == null) {

                session.setAttribute(SessionAttributeName.LOCALE, DEFAULT_LANG);

            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
