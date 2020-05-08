package com.momolela.controller;

import com.momolela.common.ResponseEntity;
import com.momolela.starter.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("success","ha");
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity();
        responseEntity.success().setData(returnMap);
        return responseEntity.toJson();
//        return "Hello World!";
    }

    @ResponseBody
    @GetMapping("/helloStarter")
    public String helloStarter() {
        return helloService.sayHelloMomolela("sunzj");
    }
}
