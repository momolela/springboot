package com.momolela;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot应用
 * 注意：这个应用的启动，是有固定的包范围，他只能把com.momolela包下的东西注册到Spring，其他包下的东西运行不了
 * @ImportResource(locations = {"classpath:beans.xml"}) 用于导入spring的xml配置文件到容器中
 */
//@ImportResource(locations = {"classpath:beans.xml"})
@MapperScan(value = "com.momolela.mapper")
@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableScheduling
public class SpringBootStart {
    public static void main(String[] args) {
        // Spring应用启动起来
        SpringApplication.run(SpringBootStart.class, args);
    }
}
