package by.epam.finalTask.service;

import by.epam.finalTask.entity.Comment;

import java.util.List;

public interface CommentService {

    boolean addComment(Comment comment) throws ServiceException;

    List<Comment> getAllComments() throws ServiceException;
}
