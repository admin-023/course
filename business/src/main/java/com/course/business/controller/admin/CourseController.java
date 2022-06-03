package com.course.business.controller.admin;

import com.course.server.dto.*;
import com.course.server.service.CourseCategoryService;
import com.course.server.service.CourseService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/course")
public class CourseController {
    private static final Logger LOG= LoggerFactory.getLogger(CourseController.class);
    @Resource
    private CourseService courseService;
    @Resource
    private CourseCategoryService courseCategoryService;

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

    /**
     * 查找课程下所有分类
     * @param courseId
     */
    @PostMapping("/list-category/{courseId}")
    public ResponseDto listCategory(@PathVariable(value = "courseId") String courseId) {
        ResponseDto responseDto = new ResponseDto();
        List<CourseCategoryDto> dtoList = courseCategoryService.listByCourse(courseId);
        responseDto.setContent(dtoList);
        return responseDto;
    }
    /*
    * 查找课程的内容*/
    @GetMapping("/find-content/{id}")
    public ResponseDto findContent(@PathVariable(value = "id") String id){
        ResponseDto responseDto=new ResponseDto();
        CourseContentDto courseContentDto=courseService.findContent(id);
        responseDto.setContent(courseContentDto);
        return  responseDto;
    }
    /*
    * 保存课程的内容
    * */
    @PostMapping("/save-content")
    public ResponseDto saveContent(@RequestBody CourseContentDto courseContentDto){
        ResponseDto responseDto=new ResponseDto();
        courseService.saveContent(courseContentDto);
        return  responseDto;
    }
    /*
    * 排序*/
    @RequestMapping(value = "/sort")
    public ResponseDto sort(@RequestBody SortDto sortDto){
        LOG.info("更新排序");
        ResponseDto responseDto=new ResponseDto();
        courseService.sort(sortDto);
        return responseDto;
    }
}
