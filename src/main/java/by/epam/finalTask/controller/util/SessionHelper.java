package by.epam.finalTask.controller.util;

import by.epam.finalTask.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionHelper {

    private SessionHelper(){
    }

    public static HttpSession getExistingSession(HttpServletRequest request) {
        return request.getSession(false);
    }

    public static HttpSession createOrGetSession(HttpServletRequest request) {
        return request.getSession(true);
    }

    public static void saveUserToSession(HttpSession session, User user) {
        session.setAttribute(SessionAttributeName.ID, user.getId());
        session.setAttribute(SessionAttributeName.LOGIN, user.getLogin());
        session.setAttribute(SessionAttributeName.USERNAME, user.getName());
        session.setAttribute(SessionAttributeName.ROLE, user.getRole());
    }
}
