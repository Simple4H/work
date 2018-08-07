package com.simple.controller;

import com.simple.common.Const;
import com.simple.common.ServerResponse;
import com.simple.pojo.User;
import com.simple.service.IUserService;
import com.simple.util.CookieUtil;
import com.simple.util.JsonUtil;
import com.simple.util.RedisPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Create By S I M P L E On 2018/07/27 14:27:55
 */
@Controller
@RequestMapping(value = "/user/")
@Slf4j
public class UserController {

    @Autowired
    private IUserService iUserService;

    // 注册
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse register(User user) {
        return iUserService.register(user);
    }

    // 登录
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse login(String email, String password, HttpServletResponse response, HttpSession session) {
        ServerResponse result = iUserService.login(email, password);
        if (result.isSuccess()) {
            CookieUtil.writeLoginToken(response, session.getId());
            RedisPoolUtil.setEx(session.getId(), Const.RedisTime.REDIS_CACHE_EXTIME, JsonUtil.obj2String(result.getData()));
            return result;
        }
        return result;
    }

    // 获取登录用户信息
    @RequestMapping(value = "get_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getInformation(HttpServletRequest request) {
        ServerResponse checkResult = iUserService.checkLoginStatus(request);
        if (checkResult.isSuccess()) {
            return checkResult;
        }
        return checkResult;
    }

}
