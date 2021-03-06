package com.course.file.controller.admin;

import com.course.file.config.FileApplication;
import com.course.server.dto.ResponseDto;
import com.course.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController /*@RestController返回json数据 @Controller返回页面*/
@RequestMapping("/admin")
public class UploadController {

    //@Value,注入属性值
    @Value("${file.domain}")
    private String FILE_DOMAIN;

    @Value("${file.path}")
    private String FILE_PATH;

    private static final Logger LOG= LoggerFactory.getLogger(FileApplication.class);

    public static final String BUSINESS_NAME="文件上传";

    @RequestMapping("/upload")
    public ResponseDto upload(@RequestParam MultipartFile file) throws IOException {
        LOG.info("上传文件开始：{}",file);
        LOG.info(file.getOriginalFilename());
        LOG.info(String.valueOf(file.getSize()));

        //文件保存到本地
        String filename=file.getOriginalFilename();
        String key= UuidUtil.getShortUuid();
        String fullpath=FILE_PATH+key+"-"+filename;
        File desk=new File(fullpath);
        if (!desk.exists()){//如果desk路径不存在，创建新文件夹
            desk.mkdirs();
        }
        file.transferTo(desk);
        LOG.info(desk.getAbsolutePath());
        ResponseDto responseDto=new ResponseDto();
        responseDto.setContent(FILE_DOMAIN+key+"-"+filename);
        return responseDto;
  }
}
