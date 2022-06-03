package com.course.server.service;

import com.course.server.domain.Teacher;
import com.course.server.domain.TeacherExample;
import com.course.server.dto.PageDto;
import com.course.server.dto.TeacherDto;
import com.course.server.mapper.TeacherMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class TeacherService {
@Resource
private TeacherMapper teacherMapper;
                public void list(PageDto pageDto) {
                PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
                TeacherExample teacherExample = new TeacherExample();
                List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
                PageInfo<Teacher> pageInfo=new PageInfo<>(teacherList);
                pageDto.setTotal(pageInfo.getTotal());
                List<TeacherDto> teacherDtoList = CopyUtil.copyList(teacherList, TeacherDto.class);
                pageDto.setList(teacherDtoList);
                }
                /*
                * 保存，id有值时更新，无值时新增
                */
                public void save(TeacherDto teacherDto) {
                Teacher teacher = CopyUtil.copy(teacherDto, Teacher.class);
                if (StringUtil.isEmpty(teacherDto.getId())){
                this.insert(teacher);
                }else{
                this.update(teacher);
                }
                }
                /*
                新增
                **/
                public void insert(Teacher teacher) {
                teacher.setId(UuidUtil.getShortUuid());
                teacherMapper.insert(teacher);
                }
                /*
                更新
                * */
                public void update(Teacher teacher) {
                teacherMapper.updateByPrimaryKey(teacher);
                }
                /*
                删除
                */
                public void delete(String id) {
                teacherMapper.deleteByPrimaryKey(id);
                }
                /**
                * 列表查询
                */
                public List<TeacherDto> all() {
                    TeacherExample teacherExample = new TeacherExample();
                    List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
                    return CopyUtil.copyList(teacherList, TeacherDto.class);
    }
                }
