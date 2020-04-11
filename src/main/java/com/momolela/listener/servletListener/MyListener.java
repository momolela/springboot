package com.momolela.listener.servletListener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Slf4j
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("===========web应用启动了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("===========web应用停止了");
    }

}
