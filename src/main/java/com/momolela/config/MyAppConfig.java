package com.momolela.config;

import com.momolela.service.HelloWorldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 指明当前类是一个配置类；
 * 就是来替代之前的Spring的xml配置文件
 * 以前在配置文件中用<bean></bean>标签添加组件，现在使用注解@Bean
 */
@Slf4j
@Configuration
public class MyAppConfig {

    /**
     * 将方法的返回值添加到容器中；
     * 容器中这个组件默认的id就是方法名
     */
    @Bean
    public HelloWorldService helloWorldService(){
        log.info("通过@Configuration和@Bean注解添加helloWorldService对象到容器中了");
        return new HelloWorldService();
    }
}
