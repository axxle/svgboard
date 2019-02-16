package ru.axxle.guestbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.axxle.guestbook.pojo.Comment;
import ru.axxle.guestbook.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Контроллер гостевой книги
 */
@Controller
public class CommentController {
    private static final String LIST_ALL_URL = "/guestbook";
    private static final String LIST_ALL_VIEW_NAME = "guestbook";
    private static final String LIST_ALL_VIEW_ATTRIBUTE_NAME = "commentsList";
    private static final String ADD_NEW_URL = "/guestbook/add";
    private static final String ADD_NEW_FORM_NAME_PARAM = "name";
    private static final String ADD_NEW_FORM_COMMENT_PARAM = "comment";
    private static final String REDIRECT_URL = "redirect:" + LIST_ALL_URL;

    @Autowired
    private CommentService commentService;

    /**
     * Получение страницы со всеми отзывами и формой отправки отзыва
     * @return - список отзывов для отображения в guestbook.jsp
     */
    @RequestMapping(
            value = LIST_ALL_URL,
            method = RequestMethod.GET)
    protected ModelAndView listAllComments() {
        ModelAndView model = new ModelAndView(LIST_ALL_VIEW_NAME);
        model.addObject(LIST_ALL_VIEW_ATTRIBUTE_NAME, commentService.listAllComments());
        return model;
    }

    /**
     * Контроллер обработки формы отправки отзыва
     * @param request - запрос с данными из формы ввода нового отзыва
     * @return - 302 редирект
     */
    @RequestMapping(
            value = ADD_NEW_URL,
            method = RequestMethod.POST)
    protected ModelAndView saveCommentAndRedirect(HttpServletRequest request,
                                                 HttpServletResponse response) {
        String name = request.getParameter(ADD_NEW_FORM_NAME_PARAM);
        String comment = request.getParameter(ADD_NEW_FORM_COMMENT_PARAM);
        ModelAndView redirectModel = null;
        if(name != null && comment != null){
            commentService.saveComment(new Comment(name, comment));
            redirectModel = new ModelAndView(REDIRECT_URL);
        }
        return redirectModel;
    }

}