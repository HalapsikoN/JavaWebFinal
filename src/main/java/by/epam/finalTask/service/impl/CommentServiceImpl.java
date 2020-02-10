package by.epam.finalTask.service.impl;

import by.epam.finalTask.dao.CommentDAO;
import by.epam.finalTask.dao.DAOException;
import by.epam.finalTask.dao.DAOFactory;
import by.epam.finalTask.entity.Comment;
import by.epam.finalTask.service.CommentService;
import by.epam.finalTask.service.ServiceException;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private static final CommentDAO commentDAO = DAOFactory.getInstance().getSqlCommentDAO();

    @Override
    public boolean addComment(Comment comment) throws ServiceException {
        if (comment == null || comment.getText() == null || comment.getDate() == null) {
            throw new ServiceException("Some of data is null");
        }

        boolean result;

        try {
            result = commentDAO.addComment(comment);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public List<Comment> getAllComments() throws ServiceException {
        List<Comment> commentList;

        try {
            commentList = commentDAO.getAllComments();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return commentList;
    }
}
