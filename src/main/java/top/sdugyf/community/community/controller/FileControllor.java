package top.sdugyf.community.community.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.sdugyf.community.community.dto.FileDTO;

@Controller
@ResponseBody
public class FileControllor {


    @RequestMapping("/file/upload")
    public FileDTO upload() {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/images/loading.gif");
        return fileDTO;
    }
}

