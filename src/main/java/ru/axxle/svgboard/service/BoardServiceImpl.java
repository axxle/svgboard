package ru.axxle.svgboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.axxle.svgboard.dao.BoardDao;
import ru.axxle.svgboard.pojo.Board;

public class BoardServiceImpl implements BoardService {

    /**
     * DAO объект для работы с БД
     */
    @Autowired
    private BoardDao dao;

    @Override
    public Board saveBoard(Board board) {
        dao.save(board);
        Board savedBoard = board;
        try {
            savedBoard = dao.getById(board.getId());
        } catch (Exception e) {
            System.out.println(e);
        }
        return savedBoard;
    }

    @Override
    public Board getById(Long id) {
        return dao.getById(id);
    }

}
