package com.course.server.service;

import com.course.server.domain.CourseContent;
import com.course.server.domain.CourseContentExample;
import com.course.server.dto.CourseContentDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseContentMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CourseContentService {
@Resource
private CourseContentMapper courseContentMapper;
                public void list(PageDto pageDto) {
                PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
                CourseContentExample courseContentExample = new CourseContentExample();
                List<CourseContent> courseContentList = courseContentMapper.selectByExample(courseContentExample);
                PageInfo<CourseContent> pageInfo=new PageInfo<>(courseContentList);
                pageDto.setTotal(pageInfo.getTotal());
                List<CourseContentDto> courseContentDtoList = CopyUtil.copyList(courseContentList, CourseContentDto.class);
                pageDto.setList(courseContentDtoList);
                }
                /*
                * 保存，id有值时更新，无值时新增
                */
                public void save(CourseContentDto courseContentDto) {
                CourseContent courseContent = CopyUtil.copy(courseContentDto, CourseContent.class);
                if (StringUtil.isEmpty(courseContentDto.getId())){
                this.insert(courseContent);
                }else{
                this.update(courseContent);
                }
                }
                /*
                新增
                **/
                public void insert(CourseContent courseContent) {
                courseContent.setId(UuidUtil.getShortUuid());
                courseContentMapper.insert(courseContent);
                }
                /*
                更新
                * */
                public void update(CourseContent courseContent) {
                courseContentMapper.updateByPrimaryKeyWithBLOBs(courseContent);//当有大字段的时候用这个updateByPrimaryKeyWithBLOBs
                }
                /*
                删除
                */
                public void delete(String id) {
                courseContentMapper.deleteByPrimaryKey(id);
                }
                }
