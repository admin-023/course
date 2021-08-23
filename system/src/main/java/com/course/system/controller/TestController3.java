package com.course.system.controller;

import com.course.server.domain.Test;
import com.course.server.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController /*@RestController返回json数据 @Controller返回页面*/
public class TestController3 {
   @Resource
    private TestService testService;
    @RequestMapping("/test")
    public List<Test> test(){
      return testService.list();
  }
}
