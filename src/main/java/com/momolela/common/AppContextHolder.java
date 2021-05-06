package com.momolela.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
public class AppContextHolder implements ApplicationContextAware {

    protected static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext = applicationContext;
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> beanType) {
        return applicationContext.getBean(beanName, beanType);
    }

    public static void removeBean(String beanName) {
        DefaultListableBeanFactory acf = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        if (acf.containsBean(beanName)) {
            acf.removeBeanDefinition(beanName);
        }
    }

    public static void addBean(String beanName, Class<?> clz, HashMap<String, Object> properties) {
        DefaultListableBeanFactory acf = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        if (acf.containsBean(beanName)) {
            acf.removeBeanDefinition(beanName);
        }
        BeanDefinitionBuilder bd = BeanDefinitionBuilder.rootBeanDefinition(clz);
        Set<String> names = properties.keySet();
        for (String nm : names) {
            bd.addPropertyValue(nm, properties.get(nm));
        }
        acf.registerBeanDefinition(beanName, bd.getBeanDefinition());
    }

    public static boolean containBean(String beanName) {
        return applicationContext.containsBean(beanName);
    }

    public static ApplicationContext get() {
        return applicationContext;
    }
}
