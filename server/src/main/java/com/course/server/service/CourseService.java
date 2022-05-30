package com.course.server.service;

import com.course.server.domain.Course;
import com.course.server.domain.CourseExample;
import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseMapper;
import com.course.server.mapper.my.MyCourseMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CourseService {
                @Resource
                private CourseMapper courseMapper;
                @Resource
                private MyCourseMapper myCourseMapper;
                @Resource
                private CourseCategoryService courseCategoryService;

                public void list(PageDto pageDto) {
                PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
                CourseExample courseExample = new CourseExample();
                List<Course> courseList = courseMapper.selectByExample(courseExample);
                        courseExample.setOrderByClause("sort asc");
                PageInfo<Course> pageInfo=new PageInfo<>(courseList);
                pageDto.setTotal(pageInfo.getTotal());
                List<CourseDto> courseDtoList = CopyUtil.copyList(courseList, CourseDto.class);
                pageDto.setList(courseDtoList);
                }
                /*
                * 保存，id有值时更新，无值时新增
                */
                @Transactional
                public void save(CourseDto courseDto) {
                Course course = CopyUtil.copy(courseDto, Course.class);
                if (StringUtil.isEmpty(courseDto.getId())){
                this.insert(course);
                }else{
                this.update(course);
                }
                //批量保存课程分类
                courseCategoryService.saveBatch(courseDto.getId(),courseDto.getCategorys());
                }
                /*
                新增
                **/
                public void insert(Course course) {
                        Date now = new Date();
                        course.setCreatedAt(now);
                        course.setUpdatedAt(now);
                course.setId(UuidUtil.getShortUuid());
                courseMapper.insert(course);
                }
                /*
                更新
                * */
                public void update(Course course) {
                        Date now = new Date();
                        course.setUpdatedAt(now);
                courseMapper.updateByPrimaryKey(course);
                }
                /*
                删除
                */
                public void delete(String id) {
                courseMapper.deleteByPrimaryKey(id);
                }
                /*
                * 更新课程时长
                * */
                public void updateTime(String courseId){
                    myCourseMapper.updateTime(courseId);
                }
                }
