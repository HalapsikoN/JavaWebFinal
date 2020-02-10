package by.epam.finalTask.app.filter;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.RequestAttributeName;
import by.epam.finalTask.controller.util.SessionAttributeName;
import by.epam.finalTask.controller.util.SessionHelper;
import by.epam.finalTask.entity.Credit;
import by.epam.finalTask.service.CreditService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BanFilter implements Filter {

    private final static Logger logger = LogManager.getLogger(BanFilter.class);

    private final static CreditService creditService = ServiceFactory.getInstance().getCreditService();
    private final String REDIRECT_PATH = "/atrack?command=ban_page";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = SessionHelper.getExistingSession(request);

        if (session != null && session.getAttribute(SessionAttributeName.ID) != null) {
            int userId = (int) session.getAttribute(SessionAttributeName.ID);

            Credit credit = null;
            try {
                credit = creditService.getOverdueCredit(userId);
            } catch (ServiceException e) {
                logger.error(e);
            }

            if (credit != null) {
                request.setAttribute(RequestAttributeName.CREDIT, credit);

                Command command = CommandProvider.getInstance().getCommand(CommandName.BAN_PAGE.name());
                try {
                    command.execute(request, response);
                } catch (CommandException e) {
                    logger.error(e);
                }
            } else {
                filterChain.doFilter(request, response);
            }

        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
