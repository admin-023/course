package com.course.server.service;

import com.course.server.domain.CourseCategory;
import com.course.server.domain.CourseCategoryExample;
import com.course.server.dto.CategoryDto;
import com.course.server.dto.CourseCategoryDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseCategoryMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CourseCategoryService {
@Resource
private CourseCategoryMapper courseCategoryMapper;
                public void list(PageDto pageDto) {
                PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
                CourseCategoryExample courseCategoryExample = new CourseCategoryExample();
                List<CourseCategory> courseCategoryList = courseCategoryMapper.selectByExample(courseCategoryExample);
                PageInfo<CourseCategory> pageInfo=new PageInfo<>(courseCategoryList);
                pageDto.setTotal(pageInfo.getTotal());
                List<CourseCategoryDto> courseCategoryDtoList = CopyUtil.copyList(courseCategoryList, CourseCategoryDto.class);
                pageDto.setList(courseCategoryDtoList);
                }
                /*
                * 保存，id有值时更新，无值时新增
                */
                public void save(CourseCategoryDto courseCategoryDto) {
                CourseCategory courseCategory = CopyUtil.copy(courseCategoryDto, CourseCategory.class);
                if (StringUtil.isEmpty(courseCategoryDto.getId())){
                this.insert(courseCategory);
                }else{
                this.update(courseCategory);
                }
                }
                /*
                新增
                **/
                public void insert(CourseCategory courseCategory) {
                courseCategory.setId(UuidUtil.getShortUuid());
                courseCategoryMapper.insert(courseCategory);
                }
                /*
                更新
                * */
                public void update(CourseCategory courseCategory) {
                courseCategoryMapper.updateByPrimaryKey(courseCategory);
                }
                /*
                删除
                */
                public void delete(String id) {
                courseCategoryMapper.deleteByPrimaryKey(id);
                }

                public void saveBatch(String courseId, List<CategoryDto> categoryDtoList){
                    CourseCategoryExample example=new CourseCategoryExample();
                    example.createCriteria().andCourseIdEqualTo(courseId);
                    courseCategoryMapper.deleteByExample(example);
                    for (int i = 0; i <categoryDtoList.size() ; i++) {
                        CourseCategory courseCategory=new CourseCategory();
                        courseCategory.setId(UuidUtil.getShortUuid());
                        courseCategory.setCourseId(courseId);
                        courseCategory.setCategoryId(categoryDtoList.get(i).getId());
                        insert(courseCategory);
                    }
                }
                }
