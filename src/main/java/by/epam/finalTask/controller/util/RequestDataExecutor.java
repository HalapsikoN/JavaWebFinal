package by.epam.finalTask.controller.util;

import javax.servlet.http.HttpServletRequest;


public class RequestDataExecutor {

    private RequestDataExecutor(){
    }

    public static String getStringByName(String parameterName, HttpServletRequest req){
        String parameter=req.getParameter(parameterName);
        return parameter;
    }

    public static Integer getIntegerByName(String parameterName, HttpServletRequest req){
        String parameter=req.getParameter(parameterName);

        Integer number;
        if(parameter!=null){
            number=Integer.valueOf(parameter);
        }else{
            number=null;
        }

        return number;
    }

    public static Double getDoubleByName(String parameterName, HttpServletRequest req){
        String parameter=req.getParameter(parameterName);

        Double number;
        if(parameter!=null){
            number=Double.valueOf(parameter);
        }else{
            number=null;
        }

        return number;
    }
}
