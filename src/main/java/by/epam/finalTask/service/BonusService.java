package by.epam.finalTask.service;

import by.epam.finalTask.entity.Bonus;

import java.util.Calendar;
import java.util.List;

public interface BonusService {

    boolean addBonus(Bonus bonus) throws ServiceException;

    Bonus getMaxValuableUserBonus(int userId) throws ServiceException;

    List<Bonus> getUserBonuses(int userId) throws ServiceException;

    List<Bonus> getUserActualBonuses(int userId) throws ServiceException;
}
