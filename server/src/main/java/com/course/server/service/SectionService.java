package com.course.server.service;

import com.course.server.domain.Section;
import com.course.server.domain.SectionExample;
import com.course.server.dto.SectionDto;
import com.course.server.dto.SectionPageDto;
import com.course.server.enums.SectionChargeEnum;
import com.course.server.mapper.SectionMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SectionService {
@Resource
private SectionMapper sectionMapper;
@Resource
private CourseService courseService;
                public void list(SectionPageDto sectionPageDto) {
                PageHelper.startPage(sectionPageDto.getPage(),sectionPageDto.getSize());
                SectionExample sectionExample = new SectionExample();
                SectionExample.Criteria criteria=sectionExample.createCriteria();
                if (!StringUtils.isEmpty(sectionPageDto.getCourseId())){
                    criteria.andCourseIdEqualTo(sectionPageDto.getCourseId());
                }
                if (!StringUtils.isEmpty(sectionPageDto.getChapterId())){
                    criteria.andChapterIdEqualTo(sectionPageDto.getChapterId());
                }
                List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
                sectionExample.setOrderByClause("sort asc");
                PageInfo<Section> pageInfo=new PageInfo<>(sectionList);
                sectionPageDto.setTotal(pageInfo.getTotal());
                List<SectionDto> sectionDtoList = CopyUtil.copyList(sectionList, SectionDto.class);
                sectionPageDto.setList(sectionDtoList);
                }
                /*
                * 保存，id有值时更新，无值时新增
                */
                @Transactional //配置事务
                public void save(SectionDto sectionDto) {
                Section section = CopyUtil.copy(sectionDto, Section.class);
                if (StringUtil.isEmpty(sectionDto.getId())){
                this.insert(section);
                }else{
                this.update(section);
                }
                courseService.updateTime(section.getCourseId());
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
