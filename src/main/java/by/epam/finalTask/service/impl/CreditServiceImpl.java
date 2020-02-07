package by.epam.finalTask.service.impl;

import by.epam.finalTask.dao.CreditDAO;
import by.epam.finalTask.dao.DAOException;
import by.epam.finalTask.dao.DAOFactory;
import by.epam.finalTask.entity.Credit;
import by.epam.finalTask.entity.User;
import by.epam.finalTask.service.CreditService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.validator.CreditDataValid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CreditServiceImpl implements CreditService {

    private static final Logger logger= LogManager.getLogger(CreditServiceImpl.class);

    private static final CreditDAO creditDAO= DAOFactory.getInstance().getSqlCreditDAO();

    @Override
    public boolean addCredit(Credit credit) throws ServiceException {
        boolean result;

        if (!CreditDataValid.isCreditValid(credit)){
            throw new ServiceException("Not valid date credit");
        }

        try {
            result=creditDAO.addCredit(credit);
        }catch (DAOException e){
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public Credit getUserCredit(int userId) throws ServiceException {
        Credit credit=null;

        try {
            credit=creditDAO.getUserCredit(userId);
        }catch (DAOException e){
            throw new ServiceException(e);
        }

        return credit;
    }

    @Override
    public Credit getActualCredit(int userId) throws ServiceException {
        Credit credit=null;

        try {
            credit=creditDAO.getActualUserCredit(userId);
        }catch (DAOException e){
            throw new ServiceException(e);
        }

        return credit;
    }

    @Override
    public Credit getOverdueCredit(int userId) throws ServiceException {
        Credit credit=null;

        try {
            credit=creditDAO.getOverdueCredit(userId);
        }catch (DAOException e){
            throw new ServiceException(e);
        }

        return credit;
    }

    @Override
    public boolean updateCreditAmount(int id, double amount) throws ServiceException {
        boolean result;

        if(!CreditDataValid.isAmountValid(amount)){
            throw new ServiceException("Not valid date credit.amount");
        }

        try {
            result=creditDAO.updateCreditAmount(id, amount);
        }catch (DAOException e){
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean deleteCredit(int id) throws ServiceException {
        boolean result;

        try {
            result=creditDAO.deleteCredit(id);
        }catch (DAOException e){
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public List<User> getAllBannedUsers() throws ServiceException {
        List<User> userList;

        try {
            userList=creditDAO.getAllBannedUsers();
        }catch (DAOException e){
            throw new ServiceException(e);
        }

        return userList;
    }

    @Override
    public boolean unbanUser(int userId) throws ServiceException {
        boolean result;

        try {
            result=creditDAO.unbanUser(userId);
        }catch (DAOException e){
            throw new ServiceException(e);
        }

        return result;
    }
}
