package com.simple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create By S I M P L E On 2018/07/27 14:27:55
 */
@Controller
@RequestMapping(value = "/user/")
public class UserController {

    @RequestMapping(value = "test.do",method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        return "ok";
    }
}
