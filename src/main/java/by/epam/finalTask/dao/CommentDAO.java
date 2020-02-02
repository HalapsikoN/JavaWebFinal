package by.epam.finalTask.dao;

import by.epam.finalTask.entity.Comment;

import java.util.List;

public interface CommentDAO {
    boolean addComment(Comment comment) throws DAOException;

    Comment getCommentById(int id) throws DAOException;

    boolean updateCommentById(int id, Comment comment) throws DAOException;

    boolean deleteCommentBId(int id) throws DAOException;

    List<Comment> getAllComments() throws DAOException;
}
