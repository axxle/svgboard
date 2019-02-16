package ru.axxle.guestbook.service;

import ru.axxle.guestbook.pojo.Comment;

import java.util.List;

public interface CommentService {

    /**
     * Сохранение нового отзыва
     * @param comment - новый отзыв
     */
    void saveComment(Comment comment);

    /**
     * Получение всех имеющихся отзывов
     * @return - список всех отзывов
     */
    List<Comment> listAllComments();

}
