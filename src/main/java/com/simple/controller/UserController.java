package com.simple.controller;

import com.simple.common.Const;
import com.simple.common.ServerResponse;
import com.simple.dao.UserMapper;
import com.simple.pojo.User;
import com.simple.util.CookieUtil;
import com.simple.util.JsonUtil;
import com.simple.util.RedisPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private UserMapper userMapper;

    @RequestMapping(value = "test.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse list(HttpServletResponse httpServletResponse, HttpSession session) {
        User user = userMapper.selectByPrimaryKey(1);
        CookieUtil.writeLoginToken(httpServletResponse,session.getId());
        RedisPoolUtil.setEx(session.getId(), Const.RedisTime.REDIS_CACHE_EXTIME, JsonUtil.obj2String(user));
        return ServerResponse.createBySuccess("success",user);

    }

}
