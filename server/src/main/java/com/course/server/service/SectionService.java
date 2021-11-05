package com.course.server.service;

import com.course.server.domain.Section;
import com.course.server.domain.SectionExample;
import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.SectionMapper;
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
public class SectionService {
@Resource
private SectionMapper chapterMapper;
                public void list(PageDto pageDto) {
                PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
                SectionExample chapterExample = new SectionExample();
                List<Section> chapterList = chapterMapper.selectByExample(chapterExample);
                PageInfo<Section> pageInfo=new PageInfo<>(chapterList);
                pageDto.setTotal(pageInfo.getTotal());
                List<SectionDto> chapterDtoList = CopyUtil.copyList(chapterList, SectionDto.class);
                pageDto.setList(chapterDtoList);
                }
                /*
                * 保存，id有值时更新，无值时新增
                */
                public void save(SectionDto chapterDto) {
                Section chapter = CopyUtil.copy(chapterDto, Section.class);
                if (StringUtil.isEmpty(chapterDto.getId())){
                this.insert(chapter);
                }else{
                this.update(chapter);
                }
                }
                /*
                新增
                **/
                public void insert(Section chapter) {
                chapter.setId(UuidUtil.getShortUuid());
                chapterMapper.insert(chapter);
                }
                /*
                更新
                * */
                public void update(Section chapter) {
                chapterMapper.updateByPrimaryKey(chapter);
                }
                /*
                删除
                */
                public void delete(String id) {
                chapterMapper.deleteByPrimaryKey(id);
                }
                }
