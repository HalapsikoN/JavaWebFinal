package by.epam.finalTask.controller;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.RequestParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private final static Logger logger= LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("WORKS!!!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        CommandProvider commandProvider=CommandProvider.getInstance();
//
//        String commandName=req.getParameter(RequestParameterName.COMMAND_NAME);
//
//        Command command=commandProvider.getCommand(commandName);
//        try{
//            command.execute(req, resp);
//        } catch (CommandException e) {
//            logger.error(e);
//            //перенаправление на страницу ошибки
//        }
        doPost(req, resp);
    }
}
