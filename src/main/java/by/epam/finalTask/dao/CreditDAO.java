package by.epam.finalTask.dao;

import by.epam.finalTask.entity.Credit;
import by.epam.finalTask.entity.User;

import java.util.List;

public interface CreditDAO {

    boolean addCredit(Credit credit) throws DAOException;

    Credit getUserCredit(int userId) throws DAOException;
    Credit getActualUserCredit(int userId) throws DAOException;
    Credit getOverdueCredit(int userId) throws DAOException;

    boolean updateCreditAmount(int id, double amount) throws DAOException;

    boolean deleteCredit(int id) throws DAOException;

    List<User> getAllBannedUsers() throws DAOException;

    boolean unbanUser(int userId) throws DAOException;
}
