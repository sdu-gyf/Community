package top.sdugyf.community.community.advice;


import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import top.sdugyf.community.community.dto.ResultDTO;
import top.sdugyf.community.community.exception.CustomizeErrorCode;
import top.sdugyf.community.community.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handel(HttpServletRequest request,
                  Throwable e,
                  Model model,
                  HttpServletResponse response) {
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            ResultDTO resultDTO;
            if(e instanceof CustomizeException){
                resultDTO = ResultDTO.errorOf((CustomizeException) e);
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYSTEM_ERROR);
            }
            try{
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {}
            return null;
        } else {
            if(e instanceof CustomizeException){
                model.addAttribute("message",e.getMessage());
            } else {
                model.addAttribute("message",CustomizeErrorCode.SYSTEM_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }


    }

}
