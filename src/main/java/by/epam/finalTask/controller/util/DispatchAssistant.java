package by.epam.finalTask.controller.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatchAssistant {

    private DispatchAssistant(){
    }

    public static void forwardToJsp(HttpServletRequest req, HttpServletResponse resp, String jspPageName) throws ServletException, IOException {

        RequestDispatcher requestDispatcher=req.getRequestDispatcher(jspPageName);

        requestDispatcher.forward(req, resp);
    }
}
