package ru.axxle.svgboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.axxle.svgboard.pojo.Board;
import ru.axxle.svgboard.service.BoardService;

/**
 * Контроллер Доски
 */
@Controller
public class BoardController {
    private static final String BOARD_URL = "/board";

    @Autowired
    private BoardService boardService;

    @RequestMapping(
            value = BOARD_URL,
            method = RequestMethod.GET)
    ResponseEntity<Board> getBoardSettings() {
        Board board = boardService.getById(1L);
        return new ResponseEntity<Board>(board, HttpStatus.OK);
    }

}