package top.sdugyf.community.community.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.sdugyf.community.community.mapper.QuestionMapper;
import top.sdugyf.community.community.model.Question;
import top.sdugyf.community.community.model.User;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {


    @Autowired
    private QuestionMapper questionMapper;


    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag ,
            HttpServletRequest request,
            Model model){

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag",tag);

        if(title==null || title == ""){
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }


        if(description==null || description == ""){
            model.addAttribute("error", "描述不能为空");
            return "publish";
        }

        if(tag==null || tag== ""){
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }



        User user = (User) request.getSession().getAttribute("user");
//        String error = "用户未登录";

        if (user==null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }
}
