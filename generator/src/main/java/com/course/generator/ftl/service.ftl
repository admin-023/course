package com.course.server.service;

import com.course.server.domain.${Domain};
import com.course.server.domain.${Domain}Example;
import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.${Domain}Mapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ${Domain}Service {
@Resource
private ${Domain}Mapper chapterMapper;
                public void list(PageDto pageDto) {
                PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
                ${Domain}Example chapterExample = new ${Domain}Example();
                List<${Domain}> chapterList = chapterMapper.selectByExample(chapterExample);
                PageInfo<${Domain}> pageInfo=new PageInfo<>(chapterList);
                pageDto.setTotal(pageInfo.getTotal());
                List<${Domain}Dto> chapterDtoList = CopyUtil.copyList(chapterList, ${Domain}Dto.class);
                pageDto.setList(chapterDtoList);
                }
                /*
                * 保存，id有值时更新，无值时新增
                */
                public void save(${Domain}Dto chapterDto) {
                ${Domain} chapter = CopyUtil.copy(chapterDto, ${Domain}.class);
                if (StringUtil.isEmpty(chapterDto.getId())){
                this.insert(chapter);
                }else{
                this.update(chapter);
                }
                }
                /*
                新增
                **/
                public void insert(${Domain} chapter) {
                chapter.setId(UuidUtil.getShortUuid());
                chapterMapper.insert(chapter);
                }
                /*
                更新
                * */
                public void update(${Domain} chapter) {
                chapterMapper.updateByPrimaryKey(chapter);
                }
                /*
                删除
                */
                public void delete(String id) {
                chapterMapper.deleteByPrimaryKey(id);
                }
                }
