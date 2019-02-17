package ru.axxle.svgboard.service;

import ru.axxle.svgboard.pojo.Board;

public interface BoardService {

    /**
     * Сохранение доски
     * @param board - доска
     */
    void saveBoard(Board board);

    /**
     * Получение доски по идентификатору
     * @return - доска
     */
    Board getById(Long id);

}
