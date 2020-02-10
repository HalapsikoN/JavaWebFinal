package by.epam.finalTask.controller.util;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;


public class RequestDataExecutor {

    private final static String OLD_CHARSET = "ISO-8859-1";
    private final static String NEW_CHARSET = "utf-8";

    private RequestDataExecutor() {
    }

    public static String getStringByName(String parameterName, HttpServletRequest req) {
        return req.getParameter(parameterName);
    }

    public static Integer getIntegerByName(String parameterName, HttpServletRequest req) {
        String parameter = req.getParameter(parameterName);

        Integer number;
        if (parameter != null) {
            number = Integer.valueOf(parameter);
        } else {
            number = null;
        }

        return number;
    }

    public static Double getDoubleByName(String parameterName, HttpServletRequest req) {
        String parameter = req.getParameter(parameterName);

        Double number;
        if (parameter != null) {
            number = Double.valueOf(parameter);
        } else {
            number = null;
        }

        return number;
    }

    public static String getStringWithWriteEncoding(HttpServletRequest req, String parameter) throws UnsupportedEncodingException {
        return new String(req.getParameter(parameter).getBytes(OLD_CHARSET), NEW_CHARSET);
    }
}
