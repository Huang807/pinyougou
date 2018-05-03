package com.pinyougou.manager.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RequestMapping("/login")
@RestController
public class LoginController {

    @RequestMapping("/userInfo")
    public Map getUserInfo(){
        //获取登录的用户的登录名称
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map map = new HashMap();
        map.put("loginName",name);
        return map;
    }
}
