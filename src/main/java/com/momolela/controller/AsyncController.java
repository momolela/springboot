package com.momolela.controller;

import com.momolela.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class AsyncController {
    @Autowired
    AsyncService asyncService;

    @ResponseBody
    @GetMapping(value = "/async")
    public String async(){
        asyncService.hello();
        return "success";
    }
}
