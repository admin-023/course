package com.course.server.service;

import com.course.server.domain.Category;
import com.course.server.domain.CategoryExample;
import com.course.server.dto.CategoryDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CategoryMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CategoryService {
@Resource
private CategoryMapper categoryMapper;

    public List<CategoryDto> all() {
        CategoryExample categoryExample = new CategoryExample();
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        categoryExample.setOrderByClause("sort asc");
        List<CategoryDto> categoryDtoList = CopyUtil.copyList(categoryList, CategoryDto.class);
        return categoryDtoList;
    }

public void list(PageDto pageDto) {
                PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
                CategoryExample categoryExample = new CategoryExample();
                List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
                        categoryExample.setOrderByClause("sort asc");
                PageInfo<Category> pageInfo=new PageInfo<>(categoryList);
                pageDto.setTotal(pageInfo.getTotal());
                List<CategoryDto> categoryDtoList = CopyUtil.copyList(categoryList, CategoryDto.class);
                pageDto.setList(categoryDtoList);
                }
                /*
                * 保存，id有值时更新，无值时新增
                */
                public void save(CategoryDto categoryDto) {
                Category category = CopyUtil.copy(categoryDto, Category.class);
                if (StringUtil.isEmpty(categoryDto.getId())){
                this.insert(category);
                }else{
                this.update(category);
                }
                }
                /*
                新增
                **/
                public void insert(Category category) {
                category.setId(UuidUtil.getShortUuid());
                categoryMapper.insert(category);
                }
                /*
                更新
                * */
                public void update(Category category) {
                categoryMapper.updateByPrimaryKey(category);
                }
                /*
                删除
                */
                @Transactional
                public void delete(String id) {
                    deleteChildren(id);
                    categoryMapper.deleteByPrimaryKey(id);
                }
                /*
                * 删除子分类
                * */
                public void deleteChildren(String id ){
                    Category category=categoryMapper.selectByPrimaryKey(id);
                    if ("00000000".equals(category.getParent())){
//                       如果是一级目录，那么它下面的二级也要被删除
                        CategoryExample categoryExample=new CategoryExample();
                        categoryExample.createCriteria().andParentEqualTo(category.getId());
                        categoryMapper.deleteByExample(categoryExample);
                    }
                }

                }
