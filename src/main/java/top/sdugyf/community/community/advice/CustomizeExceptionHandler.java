package top.sdugyf.community.community.advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import top.sdugyf.community.community.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handel(HttpServletRequest request,
                        Throwable e,
                        Model model) {
        if(e instanceof CustomizeException){
            model.addAttribute("message",e.getMessage());
        } else {
            model.addAttribute("message","服务器冒烟了。。。让服务器自愈十分钟，等会试试？十分钟后还是不好联系QQ:956895479");
        }
        return new ModelAndView("error");
    }

}
