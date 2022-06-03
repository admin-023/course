package com.course.business.controller.admin;

import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.dto.TeacherDto;
import com.course.server.service.TeacherService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/teacher")
public class TeacherController {
    private static final Logger LOG= LoggerFactory.getLogger(TeacherController.class);
    @Resource
    private TeacherService teacherService;

    @RequestMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){
        LOG.info("pageDto:{}",pageDto);
        ResponseDto responseDto = new ResponseDto();
        teacherService.list(pageDto);
        LOG.info("pageDto:{}",pageDto);
        responseDto.setContent(pageDto);
        LOG.info("responseDto:{}",responseDto);
        return responseDto;
    }

    @RequestMapping("/save")
    public ResponseDto save(@RequestBody TeacherDto teacherDto){
        LOG.info("teacherDto:{}",teacherDto);

    // 保存校验
                ValidatorUtil.require(teacherDto.getName(), "姓名");
                ValidatorUtil.length(teacherDto.getName(), "姓名", 1, 50);
                ValidatorUtil.length(teacherDto.getNickname(), "昵称", 1, 50);
                ValidatorUtil.length(teacherDto.getImage(), "头像", 1, 100);
                ValidatorUtil.length(teacherDto.getPosition(), "职位", 1, 50);
                ValidatorUtil.length(teacherDto.getMotto(), "座右铭", 1, 50);
                ValidatorUtil.length(teacherDto.getIntro(), "间介", 1, 500);
        ResponseDto responseDto = new ResponseDto();
        teacherService.save(teacherDto);
        responseDto.setContent(teacherDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        LOG.info("id:{}",id);
        ResponseDto responseDto = new ResponseDto();
        teacherService.delete(id);
        return responseDto;
    }

    @RequestMapping("/all")
    public ResponseDto all(){
        ResponseDto responseDto = new ResponseDto();
        List<TeacherDto> teacherDtoList= teacherService.all();
        responseDto.setContent(teacherDtoList);
        return responseDto;
    }
}
