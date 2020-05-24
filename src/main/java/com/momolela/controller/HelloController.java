package com.momolela.controller;

import com.momolela.annotation.ApiJsonObject;
import com.momolela.annotation.ApiJsonProperty;
import com.momolela.common.ResponseEntity;
import com.momolela.starter.HelloService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @ApiOperation(value = "这是一个方法的描述", notes = "这是一个方法的备注信息：/hello请求，返回ResponseEntity")
    @ResponseBody
    @GetMapping("/hello/{userName}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "名字", required = true, paramType = "path")
    })
    public String hello(@PathVariable String userName) {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("myDarling", userName);
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity();
        responseEntity.success().setData(returnMap);
        return responseEntity.toJson();
//        return "Hello World!";
    }

    @ApiOperation(value = "调用自定义的starter", hidden = true)
    @ResponseBody
    @GetMapping("/helloStarter")
    public String helloStarter() {
        return helloService.sayHelloMomolela("sunzj");
    }

    @ApiOperation(value = "这是一个方法的描述", notes = "这是一个方法的备注信息：/helloMap请求，返回ResponseEntity")
    @ResponseBody
    @PostMapping("/helloMap")
    public String helloMap(
            @ApiJsonObject(name = "helloMap", value = {
                    @ApiJsonProperty(key = "me", example = "sunzj", description = "me", type = "string", required = true),
                    @ApiJsonProperty(key = "you", example = "hufy", description = "you", type = "string", required = true),
                    @ApiJsonProperty(key = "allDays", example = "[5,2,0]", description = "allDays", type = "string", required = true)
            }) @RequestBody Map<String, Object> helloMap) {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("me", helloMap.get("me"));
        returnMap.put("you", helloMap.get("you"));
        returnMap.put("allDays", helloMap.get("allDays"));
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity();
        responseEntity.success().setData(returnMap);
        return responseEntity.toJson();
    }
}
