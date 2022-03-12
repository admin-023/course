package com.course.business.controller.admin;

import com.course.server.domain.Course;
import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.CourseService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/admin/course")
public class CourseController {
    private static final Logger LOG= LoggerFactory.getLogger(CourseController.class);
    @Resource
    private CourseService courseService;

    @RequestMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){
        LOG.info("pageDto:{}",pageDto);
        ResponseDto responseDto = new ResponseDto();
        courseService.list(pageDto);
        LOG.info("pageDto:{}",pageDto);
        responseDto.setContent(pageDto);
        LOG.info("responseDto:{}",responseDto);
        return responseDto;
    }

    @RequestMapping("/save")
    public ResponseDto save(@RequestBody CourseDto courseDto){
        LOG.info("courseDto:{}",courseDto);

    // 保存校验
                ValidatorUtil.require(courseDto.getName(), "名称");
                ValidatorUtil.length(courseDto.getName(), "名称", 1, 50);
                ValidatorUtil.length(courseDto.getSummary(), "概述", 1, 2000);
                ValidatorUtil.require(courseDto.getPrice(), "价格（元）");
                ValidatorUtil.length(courseDto.getImage(), "封面", 1, 100);
                ValidatorUtil.require(courseDto.getLevel(), "级别");
        ResponseDto responseDto = new ResponseDto();
        courseService.save(courseDto);
        responseDto.setContent(courseDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        LOG.info("id:{}",id);
        ResponseDto responseDto = new ResponseDto();
        courseService.delete(id);
        return responseDto;
    }
}
