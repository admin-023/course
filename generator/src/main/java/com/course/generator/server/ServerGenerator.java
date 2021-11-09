package com.course.generator.server;

import com.course.generator.util.DbUtil;
import com.course.generator.util.Field;
import com.course.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServerGenerator {
    static String moudle="business";
    static String toDtoPath= "server\\src\\main\\java\\com\\course\\server\\dto\\";
    static String toServicePath = "server\\src\\main\\java\\com\\course\\server\\service\\";
    static String toControllerPath =moudle+ "\\src\\main\\java\\com\\course\\"+moudle+"\\controller\\admin\\";
    public static void main(String[] args) throws Exception {
        String Domain="Section";
        String domain="section";
        List<Field> fieldList = DbUtil.getColumnByTableName(domain);
        Set<String> typeSet = getJavaType(fieldList);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("Domain",Domain);
        map.put("domain",domain);
        map.put("moudle",moudle);
        map.put("fieldList",fieldList);
        map.put("typeSet",typeSet);

        /*
         * 生成dto
         * */
        FreemarkerUtil.initconfig("dto.ftl");
        FreemarkerUtil.generator(toDtoPath+Domain+"Dto.java",map);

        /*
        * 生成service
        * */
        FreemarkerUtil.initconfig("service.ftl");
        FreemarkerUtil.generator(toServicePath+Domain+"Service.java",map);

        /*
         * 生成controller
         * */
        FreemarkerUtil.initconfig("controller.ftl");
        FreemarkerUtil.generator(toControllerPath+Domain+"Controller.java",map);
}

/*获取所有java类型，使用set去重*/
    public static Set<String> getJavaType(List<Field> fieldList){
        Set<String> set = new HashSet<>();
        for (int i=0;i<fieldList.size();i++){
            Field field = fieldList.get(i);
            String javaType = field.getJavaType();
            set.add(javaType);
        }
        return set;
    }
}
