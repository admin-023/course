package com.course.server.service;

import com.course.server.domain.Section;
import com.course.server.domain.SectionExample;
import com.course.server.dto.PageDto;
import com.course.server.dto.SectionDto;
import com.course.server.enums.SectionChargeEnum;
import com.course.server.mapper.SectionMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SectionService {
@Resource
private SectionMapper sectionMapper;
                public void list(PageDto pageDto) {
                PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
                SectionExample sectionExample = new SectionExample();
                List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
                        sectionExample.setOrderByClause("sort asc");
                PageInfo<Section> pageInfo=new PageInfo<>(sectionList);
                pageDto.setTotal(pageInfo.getTotal());
                List<SectionDto> sectionDtoList = CopyUtil.copyList(sectionList, SectionDto.class);
                pageDto.setList(sectionDtoList);
                }
                /*
                * 保存，id有值时更新，无值时新增
                */
                public void save(SectionDto sectionDto) {
                Section section = CopyUtil.copy(sectionDto, Section.class);
                if (StringUtil.isEmpty(sectionDto.getId())){
                this.insert(section);
                }else{
                this.update(section);
                }
                }
                /*
                新增
                **/
                public void insert(Section section) {
                        Date now = new Date();
                        section.setCreatedAt(now);
                        section.setUpdatedAt(now);
                        section.setId(UuidUtil.getShortUuid());
                        section.setCharge(SectionChargeEnum.CHARGE.getCode());
                        sectionMapper.insert(section);
                }
                /*
                更新
                * */
                public void update(Section section) {
                        Date now = new Date();
                        section.setUpdatedAt(now);
                sectionMapper.updateByPrimaryKey(section);
                }
                /*
                删除
                */
                public void delete(String id) {
                sectionMapper.deleteByPrimaryKey(id);
                }
                }
