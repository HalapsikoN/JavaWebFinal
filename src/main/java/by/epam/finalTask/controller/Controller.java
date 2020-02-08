package by.epam.finalTask.controller;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.command.Impl.NoSuchCommand;
import by.epam.finalTask.controller.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class Controller extends HttpServlet {

    private final static Logger logger = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        CommandProvider commandProvider = CommandProvider.getInstance();

        String commandName = req.getParameter(RequestParameterName.COMMAND_NAME);

        if (commandName == null) {
            commandName= CommandName.MAIN_PAGE.name();
        }

        Command command = commandProvider.getCommand(commandName);

        if(command==null){
            command=new NoSuchCommand();
        }

        try {
            command.execute(req, resp);
        } catch (CommandException e) {
            logger.error(e);
            req.setAttribute(RequestAttributeName.MESSAGE, e.getLocalizedMessage());
            DispatchAssistant.forwardToJsp(req, resp, JspPageName.ERROR_PAGE);
        }
    }
}
