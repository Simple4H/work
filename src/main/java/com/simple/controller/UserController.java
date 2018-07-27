package com.simple.controller;

import com.simple.pojo.User;
import com.simple.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Create By S I M P L E On 2018/07/27 14:27:55
 */
@Controller
@RequestMapping(value = "/user/")
@Slf4j
public class UserController {

    private final IUserService iUserService;

    @Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @RequestMapping(value = "test.do", method = RequestMethod.GET)
    @ResponseBody
    public List<User> test() {
        return iUserService.getList();
    }
}
