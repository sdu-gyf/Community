package top.sdugyf.community.community.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import top.sdugyf.community.community.dto.FileDTO;
import top.sdugyf.community.community.provider.FileProvider;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Controller
@ResponseBody
public class FileControllor {


    @Autowired
    private FileProvider fileProvider;

    @RequestMapping("/file/upload")
    public FileDTO upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        String generatedFileName="";
        try{
            assert file != null;
            generatedFileName = fileProvider.upload(file.getInputStream(), Objects.requireNonNull(file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("https://chongxincommunity.oss-cn-beijing.aliyuncs.com/chongxincommunity/"+generatedFileName);
        return fileDTO;
    }
}

