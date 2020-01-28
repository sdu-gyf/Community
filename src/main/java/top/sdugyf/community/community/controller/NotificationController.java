package top.sdugyf.community.community.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import top.sdugyf.community.community.dto.NotificationDTO;
import top.sdugyf.community.community.dto.PaginationDTO;
import top.sdugyf.community.community.enums.NotificationTypeEnum;
import top.sdugyf.community.community.mapper.NotificationMapper;
import top.sdugyf.community.community.model.User;
import top.sdugyf.community.community.service.NotificationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(@PathVariable(name="id") Long id,
                          HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        if(user==null) {
            return "redirect:/";
        }


        NotificationDTO notificationDTO = notificationService.read(id,user);
        if(NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType() || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()) {
            return "redirect:/question/"+notificationDTO.getOuterid();
        } else {
            return "redirect:/";
        }
    }
}
