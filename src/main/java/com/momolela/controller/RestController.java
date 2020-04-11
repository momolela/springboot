package com.momolela.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestController {

    @GetMapping(value = "/hi")
    public String testIntercepor() {
        return "这是一个拦截器测试类";
    }
}
