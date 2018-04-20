package org.zj.Blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.zj.Blog.bean.PicUploadBean;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RestController
public class PicUploadControllerJson {

    @Autowired
    ServletContext servletContext;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @RequestMapping("/pic_upload")
    public String uploadPic(@RequestParam(value="editormd-image-file",required = true)MultipartFile file){
        String trueFileName=file.getOriginalFilename();
        String suffix=trueFileName.substring(trueFileName.lastIndexOf("."));
        String fileName=System.currentTimeMillis()+"_"+suffix;

        String path=request.getSession().getServletContext().getRealPath("/assets/msg/upload/");
        System.out.println(path);
        File targetFile=new File(path,fileName);

        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //save
        try{
            file.transferTo(targetFile);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            return new ObjectMapper().writeValueAsString(new PicUploadBean("1","ok",servletContext.getRealPath("assets/msg/upload/")+fileName));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
