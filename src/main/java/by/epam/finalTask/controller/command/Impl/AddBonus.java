package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.util.*;
import by.epam.finalTask.entity.Bonus;
import by.epam.finalTask.service.BonusService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AddBonus implements Command {

    private static final Logger logger = LogManager.getLogger(AddBonus.class);

    private static final BonusService bonusService = ServiceFactory.getInstance().getBonusService();

    private static final SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    private static final String SUCCESS_MSG = "locale.addBonus.successMsg";
    private static final String ERROR_MSG = "locale.addBonus.errorMsg";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            HttpSession session = SessionHelper.getExistingSession(req);

            if (session == null) {
                throw new CommandException("no session");
            }

            String name = RequestDataExecutor.getStringWithWriteEncoding(req, RequestParameterName.NAME);
            int discount =RequestDataExecutor.getIntegerByName(RequestParameterName.DISCOUNT, req);
            GregorianCalendar start = new GregorianCalendar();
            Date start_date = format.parse(req.getParameter(RequestParameterName.START_DATE));
            start.setTimeInMillis(start_date.getTime());
            GregorianCalendar end = new GregorianCalendar();
            Date end_date = format.parse(req.getParameter(RequestParameterName.END_DATE));
            end.setTimeInMillis(end_date.getTime());
            int userId=RequestDataExecutor.getIntegerByName(RequestParameterName.USER_ID, req);

            Bonus bonus = new Bonus(name, discount, start, end, userId);

            boolean isAdded = bonusService.addBonus(bonus);
            Locale locale= new Locale((String) session.getAttribute(SessionAttributeName.LOCALE));
            String message;
            if (isAdded) {
                message=ResourceManager.getString(SUCCESS_MSG, locale);
                //req.setAttribute(RequestAttributeName.MESSAGE, SUCCESS_MSG);
            } else {
                message=ResourceManager.getString(ERROR_MSG, locale);
                //req.setAttribute(RequestAttributeName.MESSAGE, ERROR_MSG);
            }

//            Command  command = CommandProvider.getInstance().getCommand(CommandName.EDIT_USER_PAGE.name());
//            command.execute(req, resp);
            DispatchAssistant.redirectToCommand(req, resp, CommandName.USER_LIST, message);
        } catch (ServiceException| ServletException | IOException|ParseException e) {
            logger.error(e);
            throw new CommandException(e);
        }

    }

}
