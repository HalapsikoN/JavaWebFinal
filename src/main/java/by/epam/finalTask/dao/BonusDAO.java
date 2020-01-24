package by.epam.finalTask.dao;

import by.epam.finalTask.entity.Bonus;

public interface BonusDAO {
    boolean addBonusWithOutTracks(Bonus bonus) throws DAOException;

    Bonus getBonusById(int id) throws DAOException;

    boolean updateBonusById(int id, Bonus bonus) throws DAOException;

    boolean deleteBonusBId(int id) throws DAOException;
}
