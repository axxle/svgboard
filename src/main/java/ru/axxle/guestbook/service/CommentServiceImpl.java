package ru.axxle.guestbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.axxle.guestbook.dao.CommentDao;
import ru.axxle.guestbook.pojo.Comment;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    /**
     * DAO объект для работы с БД
     */
    @Autowired
    private CommentDao dao;

    /**
     * Сохранение отзыва с помощью DAO
     * @param comment - новый отзыв
     */
    @Override
    public void saveComment(Comment comment) {
        dao.create(comment);
    }

    /**
     * Получение всех имеющихся отзывов с помощью DAO
     * @return - список всех отзывов
     */
    @Override
    public List<Comment> listAllComments() {
        return dao.getAll();
    }

}
