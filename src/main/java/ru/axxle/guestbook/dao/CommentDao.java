package ru.axxle.guestbook.dao;

import ru.axxle.guestbook.pojo.Comment;

import java.util.List;

/**
 * DAO для работы с объектами типа "Отзыв" в гостевой книги
 */
public interface CommentDao {

    /**
     * Сохранение "Отзыва" в хранилище
     * @param comment
     */
    void create(Comment comment);

    /**
     * Получение всех имеющихся "Отзывов"
     * @return - список всех имеющихся "Отзывов"
     */
    List<Comment> getAll();

}
