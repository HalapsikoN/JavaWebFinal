package by.epam.finalTask.dao.impl;

import by.epam.finalTask.dao.CreditDAO;
import by.epam.finalTask.dao.impl.util.ConverterFromResultSet;
import by.epam.finalTask.dao.pool.ConnectionPool;
import by.epam.finalTask.dao.pool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SQLCreditDAO implements CreditDAO {

    private static final Logger logger = LogManager.getLogger(SQLCreditDAO.class);

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final ConverterFromResultSet converterFromResultSet = ConverterFromResultSet.getInstance();

    private String sqlAddCredit = "INSERT INTO credits (credit, date_end, user_id) values (?,?,?)";
    private String sqlGetCredits = "SELECT * FROM credits";
    private String sqlGetUserCredit = "SELECT * FROM credits where user_id=?";
    private String sqlUpdateCreditAmountById = "UPDATE credits SET credit=? where id=?";
    private String sqlDeleteCreditById="DELETE FROM credits where id=?";

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

}
