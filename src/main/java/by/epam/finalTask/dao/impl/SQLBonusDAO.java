package by.epam.finalTask.dao.impl;

import by.epam.finalTask.dao.BonusDAO;
import by.epam.finalTask.dao.DAOException;
import by.epam.finalTask.dao.impl.util.ConverterFromResultSet;
import by.epam.finalTask.dao.pool.ConnectionPool;
import by.epam.finalTask.dao.pool.ConnectionPoolException;
import by.epam.finalTask.entity.Bonus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.*;
import java.util.*;

public class SQLBonusDAO implements BonusDAO {

    private static final Logger logger = LogManager.getLogger(SQLUserDAO.class);

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final ConverterFromResultSet converterFromResultSet = ConverterFromResultSet.getInstance();

    private String sqlAddBonus = "INSERT INTO bonuses (name, discount, start_date, end_date, user_id) values (?,?,?,?,?)";
    private String sqlGetBonusById = "SELECT * FROM bonuses WHERE id=?";
    private String sqlUpdateBonusById = "UPDATE bonuses SET name=?, discount=?, start_date=?, end_date=?  where id=?";
    private String sqlDeleteBonusById = "DELETE FROM bonuses where id=?";
    private String sqlGetBonusesByUserId = "SELECT * FROM bonuses WHERE user_id=?";
    private String sqlGetActualBonusesByUserId = "SELECT * FROM bonuses WHERE user_id=? AND start_date<=? AND end_date>=?";

    private Map<String, PreparedStatement> preparedStatementMap;

    public SQLBonusDAO() {
        preparedStatementMap = new HashMap<>();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            logger.error(e);
        }
        prepareStatement(connection, sqlAddBonus);
        prepareStatement(connection, sqlGetBonusById);
        prepareStatement(connection, sqlUpdateBonusById);
        prepareStatement(connection, sqlDeleteBonusById);
        prepareStatement(connection, sqlGetBonusesByUserId);
        prepareStatement(connection, sqlGetActualBonusesByUserId);

        if (connection != null) {
            connectionPool.closeConnection(connection);
        }
    }

    private void prepareStatement(Connection connection, String sql) {
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatementMap.put(sql, preparedStatement);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    @Override
    public boolean addBonus(Bonus bonus) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlAddBonus);

            if (preparedStatement != null) {
                preparedStatement.setString(1, bonus.getName());
                preparedStatement.setInt(2, bonus.getDiscount());
                java.sql.Date date = new Date(bonus.getStartDate().getTimeInMillis());
                preparedStatement.setDate(3, date, bonus.getStartDate());
                date = new Date(bonus.getEndDate().getTimeInMillis());
                preparedStatement.setDate(4, date, bonus.getEndDate());
                preparedStatement.setInt(5, bonus.getUserId());

                resultRow = preparedStatement.executeUpdate();
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        if (resultRow == 0) {
            result = false;
        }
        return result;
    }

    @Override
    public Bonus getBonusById(int id) throws DAOException {
        ResultSet resultSet = null;
        Bonus bonus = null;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetBonusById);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    bonus = converterFromResultSet.getBonusFromResultSet(resultSet);
                }
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return bonus;
    }

    @Override
    public boolean updateBonusById(int id, Bonus bonus) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlUpdateBonusById);

            if (preparedStatement != null) {
                preparedStatement.setString(1, bonus.getName());
                preparedStatement.setInt(2, bonus.getDiscount());
                java.sql.Date date = new Date(bonus.getStartDate().getTimeInMillis());
                preparedStatement.setDate(3, date);
                date = new Date(bonus.getEndDate().getTimeInMillis());
                preparedStatement.setDate(4, date);
                preparedStatement.setInt(5, id);

                resultRow = preparedStatement.executeUpdate();
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        if (resultRow == 0) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean deleteBonusBId(int id) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlDeleteBonusById);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, id);

                resultRow = preparedStatement.executeUpdate();
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        if (resultRow == 0) {
            result = false;
        }
        return result;
    }

    @Override
    public List<Bonus> getBonusesByUserId(int id) throws DAOException {
        ResultSet resultSet = null;
        List<Bonus> bonusList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetBonusesByUserId);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Bonus bonus = converterFromResultSet.getBonusFromResultSet(resultSet);
                    bonusList.add(bonus);
                }
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return bonusList;
    }

    @Override
    public List<Bonus> getActualBonusesByUserId(int id) throws DAOException {
        ResultSet resultSet = null;
        List<Bonus> bonusList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetActualBonusesByUserId);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, id);
                Calendar calendar = new GregorianCalendar();
                java.sql.Date date = new java.sql.Date(calendar.getTimeInMillis());
                preparedStatement.setDate(2, date);
                preparedStatement.setDate(3, date);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Bonus bonus = converterFromResultSet.getBonusFromResultSet(resultSet);
                    bonusList.add(bonus);
                }
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return bonusList;
    }
}
