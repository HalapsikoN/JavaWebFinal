package by.epam.finalTask.service.impl;

import by.epam.finalTask.dao.BonusDAO;
import by.epam.finalTask.dao.DAOException;
import by.epam.finalTask.dao.DAOFactory;
import by.epam.finalTask.entity.Bonus;
import by.epam.finalTask.service.BonusService;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.validator.BonusDataValidator;

import java.util.List;

public class BonusServiceImpl implements BonusService {

    private BonusDAO bonusDAO = DAOFactory.getInstance().getSqlBonusDAO();

    @Override
    public boolean addBonus(Bonus bonus) throws ServiceException {
        boolean result;

        if (!BonusDataValidator.isValidBonus(bonus)) {
            throw new ServiceException("Not valid date bonus");
        }

        try {
            result = bonusDAO.addBonus(bonus);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public Bonus getMaxValuableUserBonus(int userId) throws ServiceException {
        Bonus result = null;

        List<Bonus> bonusList = getUserActualBonuses(userId);
        for (Bonus bonus : bonusList) {
            if (result == null) {
                result = bonus;
            } else {
                if (result.getDiscount() < bonus.getDiscount()) {
                    result = bonus;
                }
            }
        }

        return result;
    }

    @Override
    public List<Bonus> getUserBonuses(int userId) throws ServiceException {
        List<Bonus> bonusList;

        try {
            bonusList = bonusDAO.getBonusesByUserId(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return bonusList;
    }

    @Override
    public List<Bonus> getUserActualBonuses(int userId) throws ServiceException {
        List<Bonus> bonusList;

        try {
            bonusList = bonusDAO.getActualBonusesByUserId(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return bonusList;
    }
}
