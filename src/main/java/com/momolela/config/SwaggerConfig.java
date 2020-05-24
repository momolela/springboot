package com.momolela.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // @Value注解用于获取在配置文件中的信息
    @Value("${swagger2.apiInfo.serverUrl}")
    private String serverUrl; // 访问路径
    @Value("${swagger2.apiInfo.show}")
    private boolean swagger2Show; // 是否配置swagger,注意在正式环境使用swagger是会导致接口暴露,导致程序的安全性降低,所以只能在测试环境和开发环境中进行配置
    @Value("${swagger2.apiInfo.hostUrl}")
    private String hostUrl; // 我们访问swagger接口文档是的url,需要拼接上'/swagger-ui.html'

    @Bean
    public Docket docket1(){
        // 可以配置全局参数，比如都需要的token
        Parameter token = new ParameterBuilder().name("token")
                .description("用户登录令牌")
                .parameterType("header") // 可以设置为header和query
                .modelRef(new ModelRef("String"))
                .required(true)
                .build();
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(token);
        return new Docket(DocumentationType.SWAGGER_2).groupName("group1").globalOperationParameters(parameters);
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("group2");
    }
    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("group3");
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swagger2Show) // swagger是否启动
                .groupName("momolela")
                .host(hostUrl)
                .apiInfo(apiInfo()) // 配置swagger的基本信息
//                .ignoredParameterTypes(Integer.class) // 忽略参数，比如有些request、response、httpsession需要忽略的参数
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.momolela.controller")) // controller的位置，指定扫描的包会生成文档
                .paths(PathSelectors.any()) // 再加过滤条件
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档名称")
                .description("用于介绍该接口文档")
                .version("1.0")
                .termsOfServiceUrl(serverUrl)
                .contact(new Contact("sunzj","https://github.com/momolela","1083910359@qq.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}