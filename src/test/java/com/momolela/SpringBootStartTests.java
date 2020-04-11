package com.momolela;

import com.momolela.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @SpringBootTest说明是springboot的单元测试，支持自动注入
 * @RunWith(SpringRunner.class)说明用springboot的驱动器跑，
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootStartTests {

    // 记录器
//    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    public void useLog(){

        // 日志的级别；
        // 由低到高 trace < debug < info < warn < error
        // 可以调整输出的日志级别；日志就只会在这个级别以及以后的高级别生效。
        log.trace("记录轨道信息");
        log.debug("记录调试信息");
        // SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot的root默认规定的级别；
        log.info("记录自定义打印信息");
        log.warn("记录警告信息");
        log.error("记录错误信息");
        // 还可以使用占位符{}
        String name = "sunzj";
        int age = 25;
        log.info("name:{},age:{}",name,age);
    }

    @Test
    public void contextLoads() {
        log.info("Person Object==="+person);
    }

    @Test
    public void testHelloService() {
        boolean b = ioc.containsBean("helloService");
        log.info("HelloService Bean in context==="+b);
    }

}
