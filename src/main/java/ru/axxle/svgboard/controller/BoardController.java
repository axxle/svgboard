package ru.axxle.svgboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.axxle.svgboard.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Контроллер Доски
 */
@Controller
public class BoardController {
    private static final String BOARD_URL = "/svgboard";

    @Autowired
    private BoardService boardService;

    /** GET или POST
     * Получение параметров доски
     * @return -
     */

    /** PUT или POST
     * Сохранение обновленных параметров доски
     * @return -
     */

}