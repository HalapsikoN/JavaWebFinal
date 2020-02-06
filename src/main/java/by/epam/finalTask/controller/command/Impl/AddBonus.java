package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.RequestAttributeName;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.entity.Bonus;
import by.epam.finalTask.service.BonusService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddBonus implements Command {

    private static final Logger logger = LogManager.getLogger(AddBonus.class);

    private static final BonusService bonusService = ServiceFactory.getInstance().getBonusService();

    private static final SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    private static final String SUCCESS_MSG = "Bonus has been added!";
    private static final String ERROR_MSG = "Bonus has not been added!";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            String name = req.getParameter(RequestParameterName.NAME);
            int discount = Integer.valueOf(req.getParameter(RequestParameterName.DISCOUNT));
            GregorianCalendar start = new GregorianCalendar();
            Date start_date = format.parse(req.getParameter(RequestParameterName.START_DATE));
            start.setTimeInMillis(start_date.getTime());
            GregorianCalendar end = new GregorianCalendar();
            Date end_date = format.parse(req.getParameter(RequestParameterName.END_DATE));
            end.setTimeInMillis(end_date.getTime());
            int userId=Integer.valueOf(req.getParameter(RequestParameterName.USER_ID));

            Bonus bonus = new Bonus(name, discount, start, end, userId);

            boolean isAdded = bonusService.addBonus(bonus);

            if (isAdded) {
                req.setAttribute(RequestAttributeName.MESSAGE, SUCCESS_MSG);
            } else {
                req.setAttribute(RequestAttributeName.MESSAGE, ERROR_MSG);
            }

            Command  command = CommandProvider.getInstance().getCommand(CommandName.EDIT_USER_PAGE.name());
            command.execute(req, resp);

        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
