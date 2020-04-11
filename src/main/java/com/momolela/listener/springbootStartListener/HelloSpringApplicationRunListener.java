package com.momolela.listener.springbootStartListener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class HelloSpringApplicationRunListener implements SpringApplicationRunListener {
    // 必须有的构造器
    public HelloSpringApplicationRunListener(SpringApplication application, String[] args) {
    }

    @Override
    public void starting() {
        log.info("SpringApplicationRunListener...starting...");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        Object o = environment.getSystemProperties().get("os.name");
        log.info("SpringApplicationRunListener...environmentPrepared.." + o);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("SpringApplicationRunListener...contextPrepared...");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("SpringApplicationRunListener...contextLoaded...");
    }
}

