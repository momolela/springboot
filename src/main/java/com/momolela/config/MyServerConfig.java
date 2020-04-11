package com.momolela.config;

import com.momolela.filter.MyFilter;
import com.momolela.listener.servletListener.MyListener;
import com.momolela.servlet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @Configuration 指明当前类是一个配置类；就是来替代之前的Spring的xml配置文件
 * 以前在配置文件中用<bean></bean>标签添加组件，现在使用注解@Bean
 */
@Configuration
public class MyServerConfig {

    /**
     * 注册Servlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean registMyServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet(), "/myServlet");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }

    /**
     * 注册Filter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean registMyFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/myFilter", "/myServlet"));
        return filterRegistrationBean;
    }

    /**
     * 注册Listener
     *
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean registMyListener() {
        ServletListenerRegistrationBean<MyListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>(new MyListener());
        return servletListenerRegistrationBean;
    }

    /**
     * 嵌入式的Servlet容器的定制器；来修改Servlet容器的
     * 配置
     *
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8081);
            }
        };
    }
}
