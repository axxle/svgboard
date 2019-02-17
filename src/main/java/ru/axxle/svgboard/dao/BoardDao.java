package ru.axxle.svgboard.dao;

import ru.axxle.svgboard.pojo.Board;

/**
 * DAO для работы с объектами типа "Доска"
 */
public interface BoardDao {

    /**
     * Сохранение "Доски" в хранилище
     * @param board
     */
    void save(Board board);

    /**
     * Получение "Доски" по идентификатору
     * @return - объект "Доска"
     */
    Board getById(Long id);

}
