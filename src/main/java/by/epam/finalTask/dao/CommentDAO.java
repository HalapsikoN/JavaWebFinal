package by.epam.finalTask.dao;

import by.epam.finalTask.entity.Comment;

public interface CommentDAO {
    boolean addCommentWithOutTracks(Comment comment) throws DAOException;

    Comment getCommentById(int id) throws DAOException;

    boolean updateCommentById(int id, Comment comment) throws DAOException;

    boolean deleteCommentBId(int id) throws DAOException;
}
