package by.epam.finalTask.service;

import by.epam.finalTask.entity.Credit;
import by.epam.finalTask.entity.User;

import java.util.List;

public interface CreditService {

    boolean addCredit(Credit credit) throws ServiceException;

    Credit getUserCredit(int userId) throws ServiceException;
    Credit getActualCredit(int userId) throws ServiceException;
    Credit getOverdueCredit(int userId) throws ServiceException;

    boolean updateCreditAmount(int id, double amount) throws ServiceException;

    boolean deleteCredit(int id) throws ServiceException;

    List<User> getAllBannedUsers() throws ServiceException;

    boolean unbanUser(int userId) throws ServiceException;

}
