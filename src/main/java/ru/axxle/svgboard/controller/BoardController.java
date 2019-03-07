package ru.axxle.svgboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.axxle.svgboard.jakson.JaksonBoard;
import ru.axxle.svgboard.pojo.Board;
import ru.axxle.svgboard.service.BoardService;

/**
 * Контроллер Доски
 */
@RestController
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

    @RequestMapping(
            value = BOARD_URL,
            method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Board> saveBoardSettings(@RequestBody JaksonBoard jaksonBoard) {
        Board savedBoard = boardService.saveBoard(new Board(jaksonBoard.getId(),
                jaksonBoard.getWidth(),jaksonBoard.getHeight(),jaksonBoard.getViewbox()));
        return new ResponseEntity<Board>(savedBoard, HttpStatus.OK);
    }

}