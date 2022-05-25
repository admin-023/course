package com.course.business.controller.admin;

import com.course.server.dto.CategoryDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.CategoryService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {
    private static final Logger LOG= LoggerFactory.getLogger(CategoryController.class);
    @Resource
    private CategoryService categoryService;

    @RequestMapping("/all")
    public ResponseDto all(){
        ResponseDto responseDto = new ResponseDto();
        List<CategoryDto> categoryDtoList= categoryService.all();
        responseDto.setContent(categoryDtoList);
        return responseDto;
    }

    @RequestMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){
        LOG.info("pageDto:{}",pageDto);
        ResponseDto responseDto = new ResponseDto();
        categoryService.list(pageDto);
        LOG.info("pageDto:{}",pageDto);
        responseDto.setContent(pageDto);
        LOG.info("responseDto:{}",responseDto);
        return responseDto;
    }

    @RequestMapping("/save")
    public ResponseDto save(@RequestBody CategoryDto categoryDto){
        LOG.info("categoryDto:{}",categoryDto);

    // 保存校验
                ValidatorUtil.require(categoryDto.getParent(), "父id");
                ValidatorUtil.require(categoryDto.getName(), "名称");
                ValidatorUtil.length(categoryDto.getName(), "名称", 1, 50);
        ResponseDto responseDto = new ResponseDto();
        categoryService.save(categoryDto);
        responseDto.setContent(categoryDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        LOG.info("id:{}",id);
        ResponseDto responseDto = new ResponseDto();
        categoryService.delete(id);
        return responseDto;
    }
}
