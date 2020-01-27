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
        if(req.getParameter(RequestParameterName.COMMAND_NAME)==null){
            req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
        }else{
            System.out.println("asdasdadsadasddas");
            doPost(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandProvider commandProvider=CommandProvider.getInstance();

        String commandName=req.getParameter(RequestParameterName.COMMAND_NAME);

        System.out.println(commandName);

        Command command=commandProvider.getCommand(commandName);
        System.out.println(command);
        try{
            command.execute(req, resp);
        } catch (CommandException e) {
            logger.error(e);
            //перенаправление на страницу ошибки

        }


    }
}
