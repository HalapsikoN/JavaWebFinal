package by.epam.finalTask.controller;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.command.CommandName;
import by.epam.finalTask.controller.command.CommandProvider;
import by.epam.finalTask.controller.util.DispatchAssistant;
import by.epam.finalTask.controller.util.JspPageName;
import by.epam.finalTask.controller.util.RequestParameterName;
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

        logger.info("Works");
        System.out.println(req.getCharacterEncoding());
        System.out.println(resp.getCharacterEncoding());
        System.out.println(req.getContextPath());
        System.out.println(req.getServletPath());
        System.out.println();
        File f=new File("file.txt");
        System.out.println(f.getAbsolutePath());


        String commandName = req.getParameter(RequestParameterName.COMMAND_NAME);

        if (commandName == null) {
            commandName= CommandName.MAIN_PAGE.name();
            //DispatchAssistant.forwardToJsp(req, resp, JspPageName.MAIN_PAGE);
            //req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
        }

        System.out.println(commandName);

        Command command = commandProvider.getCommand(commandName);
        System.out.println(command);
        try {
            command.execute(req, resp);
        } catch (CommandException e) {
            logger.error(e);
            //перенаправление на страницу ошибки
            System.out.println(e);
        }
    }
}
