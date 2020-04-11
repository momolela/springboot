package com.momolela.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 将配置文件application.yaml中的配置的属性值映射到当前组件
 * ConfigurationProperties用于告诉springboot将本类中的所有属性和配置文件application.yaml中的相关配置进行绑定
 * prefix = "person"指定和配置文件中person下的所有属性进行一一映射
 *
 * 只有这个组件是容器中的组件，才能使用@ConfigurationProperties的功能，所以加上@Component注解
 *
 * @ConfigurationProperties(prefix = "person") 默认从全局配置文件（application.properties|application.yaml）中获取值
 */
@PropertySource(value = {"classpath:person.properties"})
@Data
@Component
@ConfigurationProperties(prefix = "person")
//@Validated
public class Person implements Serializable {

    /**
     * <bean class="Person">
     *     <property name="lastName" value="字面量 | ${}从环境变量配置文件中获取值 | #{SpEl}"></property>
     * </bean>
     */

//    @Value("${person.last-name}")
//    @Email
    private String lastName;
//    @Value("#{11*2}")
    private Integer age;
//    @Value("true")
    private Boolean boss;
    private Date birth;
    private Map<String, Object> map;
    private List<Object> list;
    private Dog dog;

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", map=" + map +
                ", list=" + list +
                ", dog=" + dog +
                '}';
    }
}
