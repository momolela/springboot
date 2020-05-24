package com.momolela.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class RestController {

    @GetMapping(value = "/hi")
    public String testIntercepor() {
        return "这是一个拦截器测试类";
    }
}
