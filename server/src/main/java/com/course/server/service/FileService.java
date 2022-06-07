package com.course.server.service;

import com.course.server.domain.File;
import com.course.server.domain.FileExample;
import com.course.server.dto.FileDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.FileMapper;
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

        import java.util.Date;

@Service
public class FileService {
@Resource
private FileMapper fileMapper;
                public void list(PageDto pageDto) {
                PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
                FileExample fileExample = new FileExample();
                List<File> fileList = fileMapper.selectByExample(fileExample);
                PageInfo<File> pageInfo=new PageInfo<>(fileList);
                pageDto.setTotal(pageInfo.getTotal());
                List<FileDto> fileDtoList = CopyUtil.copyList(fileList, FileDto.class);
                pageDto.setList(fileDtoList);
                }
                /*
                * 保存，id有值时更新，无值时新增
                */
                public void save(FileDto fileDto) {
                File file = CopyUtil.copy(fileDto, File.class);
                if (StringUtil.isEmpty(fileDto.getId())){
                this.insert(file);
                }else{
                this.update(file);
                }
                }
                /*
                新增
                **/
                public void insert(File file) {
                        Date now = new Date();
                        file.setCreatedAt(now);
                        file.setUpdatedAt(now);
                file.setId(UuidUtil.getShortUuid());
                fileMapper.insert(file);
                }
                /*
                更新
                * */
                public void update(File file) {
                        Date now = new Date();
                        file.setUpdatedAt(now);
                fileMapper.updateByPrimaryKey(file);
                }
                /*
                删除
                */
                public void delete(String id) {
                fileMapper.deleteByPrimaryKey(id);
                }
                }
