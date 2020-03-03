package by.epam.finalTask.controller.util;

import by.epam.finalTask.controller.command.CommandName;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class DispatchAssistant {

    private static final String PATH = "?command=";
    private static final String MESSAGE = "&message=";
    private static final String ENCODING = "UTF-8";

    private DispatchAssistant() {
    }

    public static void forwardToJsp(HttpServletRequest req, HttpServletResponse resp, String jspPageName) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(jspPageName);

        requestDispatcher.forward(req, resp);
    }

    public static void redirectToCommand(HttpServletRequest req, HttpServletResponse resp, CommandName commandName) throws ServletException, IOException {

        String url = req.getRequestURL().toString();

        resp.sendRedirect(url + PATH + commandName);
    }

    public static void redirectToCommand(HttpServletRequest req, HttpServletResponse resp, CommandName commandName, String message) throws ServletException, IOException {

        String startUrl = req.getRequestURL().toString();
        String url=startUrl + PATH + commandName + MESSAGE + URLEncoder.encode(message, ENCODING);
        resp.sendRedirect(url);
    }
}
