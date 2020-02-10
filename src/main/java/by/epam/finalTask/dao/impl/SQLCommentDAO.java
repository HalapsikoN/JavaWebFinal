package by.epam.finalTask.dao.impl;

import by.epam.finalTask.dao.CommentDAO;
import by.epam.finalTask.dao.DAOException;
import by.epam.finalTask.dao.impl.util.ConverterFromResultSet;
import by.epam.finalTask.dao.pool.ConnectionPool;
import by.epam.finalTask.dao.pool.ConnectionPoolException;
import by.epam.finalTask.entity.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLCommentDAO implements CommentDAO {

    private static final Logger logger = LogManager.getLogger(SQLUserDAO.class);

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final ConverterFromResultSet converterFromResultSet = ConverterFromResultSet.getInstance();

    private String sqlAddComment = "INSERT INTO comments (user_id, date, track_id, text) values (?,?,?,?)";
    private String sqlGetCommentById = "SELECT * FROM comments WHERE id=?";
    private String sqlUpdateCommentById = "UPDATE comments SET user_id=?, date=?, track_id=?, text=? where id=?";
    private String sqlDeleteCommentById = "DELETE FROM comments where id=?";
    private String sqlGetAllComments = "SELECT * FROM comments";

    private Map<String, PreparedStatement> preparedStatementMap;

    public SQLCommentDAO() {
        preparedStatementMap = new HashMap<>();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            logger.error(e);
        }
        prepareStatement(connection, sqlAddComment);
        prepareStatement(connection, sqlGetCommentById);
        prepareStatement(connection, sqlUpdateCommentById);
        prepareStatement(connection, sqlDeleteCommentById);
        prepareStatement(connection, sqlGetAllComments);

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
    public boolean addComment(Comment comment) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlAddComment);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, comment.getUserId());
                java.sql.Timestamp date = new Timestamp(comment.getDate().getTimeInMillis());
                preparedStatement.setTimestamp(2, date);
                preparedStatement.setInt(3, comment.getTrackId());
                preparedStatement.setString(4, comment.getText());

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
    public Comment getCommentById(int id) throws DAOException {
        ResultSet resultSet = null;
        Comment comment = null;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetCommentById);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    comment = converterFromResultSet.getCommentFromResultSet(resultSet);
                }
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return comment;
    }

    @Override
    public boolean updateCommentById(int id, Comment comment) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlUpdateCommentById);

            if (preparedStatement != null) {
                preparedStatement.setInt(1, comment.getUserId());
                java.sql.Timestamp date = new Timestamp(comment.getDate().getTimeInMillis());
                preparedStatement.setTimestamp(2, date);
                preparedStatement.setInt(3, comment.getTrackId());
                preparedStatement.setString(4, comment.getText());
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
    public boolean deleteCommentBId(int id) throws DAOException {
        boolean result = true;
        int resultRow;

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlDeleteCommentById);

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
    public List<Comment> getAllComments() throws DAOException {
        ResultSet resultSet = null;
        List<Comment> commentList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = preparedStatementMap.get(sqlGetAllComments);

            if (preparedStatement != null) {

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Comment comment = converterFromResultSet.getCommentFromResultSet(resultSet);
                    if (comment != null) {
                        commentList.add(comment);
                    }
                }
            } else {
                throw new DAOException("Couldn't find prepared statement");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return commentList;
    }
}
