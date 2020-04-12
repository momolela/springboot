package com.momolela.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class KungfuController {
    private final String PREFIX = "pages/";

    /**
     * 欢迎页
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "welcome";
    }

    /**
     * 登陆页
     *
     * @return
     */
    @GetMapping("/userlogin")
    public String loginPage() {
        return PREFIX + "login";
    }


    /**
     * level1页面映射
     *
     * @param path
     * @return
     */
    @GetMapping("/level1/{path}")
    public String level1(@PathVariable("path") String path) {
        System.out.println("当前登录的用户是：" + getUserName());
        return PREFIX + "level1/" + path;
    }

    /**
     * level2页面映射
     *
     * @param path
     * @return
     */
    @GetMapping("/level2/{path}")
    public String level2(@PathVariable("path") String path) {
        System.out.println("当前登录的用户是：" + getUserName());
        return PREFIX + "level2/" + path;
    }

    /**
     * level3页面映射
     *
     * @param path
     * @return
     */
    @GetMapping("/level3/{path}")
    public String level3(@PathVariable("path") String path) {
        System.out.println("当前登录的用户是：" + getUserName());
        return PREFIX + "level3/" + path;
    }

    /**
     * 从当前线程中获取当前会话登录用户的用户名
     *
     * @return
     */
    private String getUserName() {
        String userName = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            userName = userDetails.getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
