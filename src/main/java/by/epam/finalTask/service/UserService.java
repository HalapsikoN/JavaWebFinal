package by.epam.finalTask.service;

import by.epam.finalTask.entity.User;

public interface UserService {

    User signIn(String login, String password) throws ServiceException;

    boolean register(User user, String password) throws ServiceException;
}
