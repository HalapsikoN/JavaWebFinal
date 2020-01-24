package by.epam.finalTask.service;

import by.epam.finalTask.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory instance=new ServiceFactory();

    private final UserService userService=new UserServiceImpl();

    private ServiceFactory(){
    }

    public static ServiceFactory getInstance(){
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }
}
