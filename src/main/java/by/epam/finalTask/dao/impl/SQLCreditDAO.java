package by.epam.finalTask.dao.impl;

import by.epam.finalTask.dao.CreditDAO;
import by.epam.finalTask.dao.DAOException;
import by.epam.finalTask.dao.impl.util.ConverterFromResultSet;
import by.epam.finalTask.dao.pool.ConnectionPool;
import by.epam.finalTask.dao.pool.ConnectionPoolException;
import by.epam.finalTask.entity.Credit;
import by.epam.finalTask.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class SQLCreditDAO implements CreditDAO {

    private static final Logger logger = LogManager.getLogger(SQLCreditDAO.class);

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final ConverterFromResultSet converterFromResultSet = ConverterFromResultSet.getInstance();

    private String sqlAddCredit = "INSERT INTO credits (credit, date_end, user_id) values (?,?,?)";
    private String sqlGetCredits = "SELECT * FROM credits";
    private String sqlGetUserCredit = "SELECT * FROM credits where user_id=?";
    private String sqlUpdateCreditAmountById = "UPDATE credits SET credit=? where id=?";
    private String sqlDeleteCreditById="DELETE FROM credits where id=?";
    private String sqlGetActualUserCredit="SELECT * FROM credits where user_id=? AND date_end>=?";
    private String sqlGetOverdueCredit="SELECT * FROM credits where user_id=? AND date_end<?";
    private String sqlGetAllBannedUsers="SELECT users.* FROM credits INNER JOIN users ON users.id=credits.user_id where date_end<?";
    private String sqlUnbanUser="DELETE FROM credits where user_id=?";

    private Map<String, PreparedStatement> preparedStatementMap;

    public SQLCreditDAO() {
        preparedStatementMap = new HashMap<>();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            logger.error(e);
        }
        prepareStatement(connection, sqlAddCredit);
        prepareStatement(connection, sqlGetCredits);
        prepareStatement(connection, sqlGetUserCredit);
        prepareStatement(connection, sqlUpdateCreditAmountById);
        prepareStatement(connection, sqlDeleteCreditById);
        prepareStatement(connection, sqlGetActualUserCredit);
        prepareStatement(connection, sqlGetOverdueCredit);
        prepareStatement(connection, sqlGetAllBannedUsers);
        prepareStatement(connection, sqlUnbanUser);


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
    public boolean addCredit(Credit credit) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlAddCredit);

            if (preparedStatement != null) {
                preparedStatement.setDouble(1, credit.getAmount());
                java.sql.Date date = new Date(credit.getDate().getTimeInMillis());
                preparedStatement.setDate(2, date, credit.getDate());
                preparedStatement.setInt(3, credit.getUserId());

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
    public Credit getUserCredit(int userId) throws DAOException {
        ResultSet resultSet = null;
        Credit credit = null;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetUserCredit);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, userId);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    credit = converterFromResultSet.getCreidtFromResultSet(resultSet);
                }
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return credit;
    }

    @Override
    public Credit getActualUserCredit(int userId) throws DAOException {
        ResultSet resultSet = null;
        Credit credit = null;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetActualUserCredit);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, userId);
                Calendar calendar=new GregorianCalendar();
                java.sql.Date date=new java.sql.Date(calendar.getTimeInMillis());
                preparedStatement.setDate(2, date);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    credit = converterFromResultSet.getCreidtFromResultSet(resultSet);
                }
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return credit;
    }

    @Override
    public Credit getOverdueCredit(int userId) throws DAOException {
        ResultSet resultSet = null;
        Credit credit = null;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetOverdueCredit);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, userId);
                Calendar calendar=new GregorianCalendar();
                java.sql.Date date=new java.sql.Date(calendar.getTimeInMillis());
                preparedStatement.setDate(2, date);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    credit = converterFromResultSet.getCreidtFromResultSet(resultSet);
                }
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return credit;
    }

    @Override
    public boolean updateCreditAmount(int id, double amount) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlUpdateCreditAmountById);

            if (preparedStatement != null) {
                preparedStatement.setDouble(1, amount);
                preparedStatement.setInt(2, id);

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
    public boolean deleteCredit(int id) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlDeleteCreditById);

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
    public List<User> getAllBannedUsers() throws DAOException {
        List<User> result=new ArrayList<>();
        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetAllBannedUsers);

            if (preparedStatement != null) {
                Calendar calendar=new GregorianCalendar();
                java.sql.Date date=new java.sql.Date(calendar.getTimeInMillis());
                preparedStatement.setDate(1, date, calendar);

                resultSet = preparedStatement.executeQuery();
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
            while (resultSet.next()) {
                User user = converterFromResultSet.getUserFromResultSet(resultSet);
                result.add(user);
            }
        }catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return result;
    }

    @Override
    public boolean unbanUser(int userId) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlUnbanUser);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, userId);

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
}
