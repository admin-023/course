package com.course.business.controller.admin;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.ChapterPageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {
    private static final Logger LOG= LoggerFactory.getLogger(ChapterController.class);
    @Resource
    private ChapterService chapterService;

    @RequestMapping("/list")
    public ResponseDto list(@RequestBody ChapterPageDto chapterPageDto){
        LOG.info("chapterPageDto:{}",chapterPageDto);
        ResponseDto responseDto = new ResponseDto();
        ValidatorUtil.require(chapterPageDto.getCourseId(),"课程ID");
        chapterService.list(chapterPageDto);
        LOG.info("chapterPageDto:{}",chapterPageDto);
        responseDto.setContent(chapterPageDto);
        LOG.info("responseDto:{}",responseDto);
        return responseDto;
    }

    @RequestMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto){
        LOG.info("chapterDto:{}",chapterDto);
        //        增加传入过来的后端校验
        ValidatorUtil.require(chapterDto.getName(),"课程");
        ValidatorUtil.require(chapterDto.getCourseId(),"课程ID");
        ValidatorUtil.length(chapterDto.getCourseId(),"课程ID",1,8);

        ResponseDto responseDto = new ResponseDto();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        LOG.info("id:{}",id);
        ResponseDto responseDto = new ResponseDto();
        chapterService.delete(id);
        return responseDto;
    }
}
