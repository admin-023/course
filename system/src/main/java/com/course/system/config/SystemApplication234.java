package com.course.system.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.course") /*用来扫描@Controller  @Service  @Repository这类,主要就是定义扫描的路径从中找出标志了需要装配的类到Spring容器中*/
@MapperScan("com.course.server.mapper")/*扫描mapper类的注解,就不用在每个mapper类上加@MapperScan了*/
public class SystemApplication234 {

	private static final Logger LOG= LoggerFactory.getLogger(SystemApplication234.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SystemApplication234.class);
		Environment env = app.run(args).getEnvironment();//getEnviroment得到与操作系统，处理器，屏幕等系统相关信息
		LOG.info("启动成功");
		LOG.info("System地址：\thttp://127.0.0.1:{}",env.getProperty("server.port"));
	}
}
