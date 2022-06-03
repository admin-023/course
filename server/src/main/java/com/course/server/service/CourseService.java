package com.course.server.service;

import com.course.server.domain.Course;
import com.course.server.domain.CourseContent;
import com.course.server.domain.CourseExample;
import com.course.server.dto.CourseContentDto;
import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.SortDto;
import com.course.server.mapper.CourseContentMapper;
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
                @Resource
                private CourseContentMapper courseContentMapper;

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
                courseCategoryService.saveBatch(course.getId(),courseDto.getCategorys());
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
                /*
                * 查找课程内容*/
                public CourseContentDto findContent(String id){
                    CourseContent courseContent = courseContentMapper.selectByPrimaryKey(id);
                    if (courseContent==null){
                        return null;
                    }
                    CourseContentDto courseContentDto=CopyUtil.copy(courseContent,CourseContentDto.class);
                    return courseContentDto;
                }
                /*
                * 保存课程内容,包含新增和修改*/
                public int saveContent(CourseContentDto courseContentDto){
                    CourseContent courseContent=CopyUtil.copy(courseContentDto,CourseContent.class);
                    int i=courseContentMapper.updateByPrimaryKeyWithBLOBs(courseContent);//返回修改的行数
                    if (i==0){//如果修改的行数为零，那么就是新增
                        i=courseContentMapper.insert(courseContent);
                    }
                    return i;
                }
                /*
                * 更新排序
                * */
                public void sort(SortDto sortDto){
                    /*更新新的排序*/
                    myCourseMapper.updateSort(sortDto);

                    /*如果排序值变小*/
                    if (sortDto.getNewSort()<sortDto.getOldSort()){
                        myCourseMapper.moveSortBackward(sortDto);
                    }
                    /*如果排序值变大*/
                    if (sortDto.getOldSort()<sortDto.getNewSort()){
                        myCourseMapper.moveSortForward(sortDto);
                    }
                }
                }
