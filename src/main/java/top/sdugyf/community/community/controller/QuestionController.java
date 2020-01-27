package top.sdugyf.community.community.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.sdugyf.community.community.dto.CommentCreateDTO;
import top.sdugyf.community.community.dto.CommentDTO;
import top.sdugyf.community.community.dto.QuestionDTO;
import top.sdugyf.community.community.service.CommentService;
import top.sdugyf.community.community.service.QuestionService;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model) {

        QuestionDTO questionDTO = questionService.getById(id);

        List<CommentDTO> comments = commentService.listByQuestionId(id);

        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        return "question";
    }
}
