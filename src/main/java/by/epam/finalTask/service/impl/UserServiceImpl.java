package by.epam.finalTask.service.impl;

import by.epam.finalTask.dao.DAOException;
import by.epam.finalTask.dao.DAOFactory;
import by.epam.finalTask.dao.UserDAO;
import by.epam.finalTask.entity.User;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.UserService;
import by.epam.finalTask.service.validator.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {

    public static final Logger logger= LogManager.getLogger(UserServiceImpl.class);

    private UserDAO userDAO= DAOFactory.getInstance().getSqlUserDAO();

    @Override
    public User signIn(String login, String password) throws ServiceException {
        User user=null;

        if(!isValidData(login, password)){
            throw new ServiceException("Not valid data");
        }

        //закодировать пароль!!!!!!

        try {
            String realPassword=userDAO.getUserPasswordByLogin(login);
            if(password.equals(realPassword)){
                user=userDAO.getUserByLogin(login);
            }
        } catch (DAOException e) {
            throw new ServiceException("Cannot perform action with data source", e);
        }

        return user;
    }

    @Override
    public boolean register(User user, String password) throws ServiceException {
        boolean result=false;

        if(!isValidData(user, password)){
            throw new ServiceException("Not valid data");
        }

        try {
            boolean isBusyLogin=(userDAO.getUserByLogin(user.getLogin())!=null);

            //закодироавать пароль!!!!!!

            if(!isBusyLogin){
                userDAO.addUser(user, password);
                result=true;
            }
        } catch (DAOException e) {
            throw new ServiceException("Cannot perform action with data source", e);
        }

        return result;
    }

    private boolean isValidData(String login, String password){
        boolean validLogin= UserDataValidator.isValidLogin(login);
        boolean validPassword=UserDataValidator.isValidPassword(password);

        return (validLogin&&validPassword);
    }

    private boolean isValidData(User user, String password){
        boolean validUser= UserDataValidator.isUserValid(user);
        boolean validPassword=UserDataValidator.isValidPassword(password);

        return (validUser&&validPassword);
    }
}
