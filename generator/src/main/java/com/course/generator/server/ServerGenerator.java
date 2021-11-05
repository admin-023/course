package com.course.generator.server;

import com.course.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;

public class ServerGenerator {
    static String toPath = "generator\\src\\main\\java\\com\\course\\generator\\test\\";//生成路径
    public static void main(String[] args) throws IOException, TemplateException {
        FreemarkerUtil.initconfig("test.ftl");
        FreemarkerUtil.generator(toPath+"Test.java");
    }
}
