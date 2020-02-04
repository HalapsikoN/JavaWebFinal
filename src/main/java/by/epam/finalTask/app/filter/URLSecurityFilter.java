package by.epam.finalTask.app.filter;

import by.epam.finalTask.controller.util.JspPageName;
import by.epam.finalTask.controller.util.SessionHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class URLSecurityFilter implements Filter {

    private Set<String> protectedURL =new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedURL.add("/atrack");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        HttpSession session= SessionHelper.getExistingSession(request);

        if(!protectedURL.contains(request.getRequestURI())){
            filterChain.doFilter(request, response);
        }else {
            response.sendRedirect(request.getContextPath()+ "/atrack");
        }
    }

    @Override
    public void destroy() {

    }
}
