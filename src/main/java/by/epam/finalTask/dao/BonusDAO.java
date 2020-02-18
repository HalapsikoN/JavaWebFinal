package by.epam.finalTask.dao;

import by.epam.finalTask.entity.Bonus;

import java.util.List;

public interface BonusDAO {
    boolean addBonus(Bonus bonus) throws DAOException;

    Bonus getBonusById(int id) throws DAOException;

    boolean updateBonusById(int id, Bonus bonus) throws DAOException;

    boolean deleteBonusBId(int id) throws DAOException;

    List<Bonus> getBonusesByUserId(int id) throws DAOException;
    List<Bonus> getActualBonusesByUserId(int id) throws DAOException;
}
