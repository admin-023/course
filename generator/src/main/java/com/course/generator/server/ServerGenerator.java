package com.course.generator.server;

import com.course.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;

public class ServerGenerator {
//    static String toPath = "generator\\src\\main\\java\\com\\course\\generator\\test\\";//生成路径
    static String toServicePath = "server\\src\\main\\java\\com\\course\\server\\service\\";
    public static void main(String[] args) throws IOException, TemplateException {
        String Domain="Section";
        String domain="section";
        HashMap<Object, Object> map = new HashMap<>();
        map.put("Domain",Domain);
        map.put("domain",domain);

//        FreemarkerUtil.initconfig("test.ftl");
//        FreemarkerUtil.generator(toPath+"Test.java");

        FreemarkerUtil.initconfig("service.ftl");
        FreemarkerUtil.generator(toServicePath+Domain+"Service.java",map);
}
}
