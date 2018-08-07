package com.simple.controller;

import com.simple.common.ServerResponse;
import com.simple.pojo.User;
import com.simple.service.IDetailService;
import com.simple.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Create By S I M P L E On 2018/08/01 15:04:28
 */
@Controller
@RequestMapping(value = "/detail/")
public class DetailController {

    @Autowired
    private IDetailService iDetailService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "create_detail.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse createDetail(HttpServletRequest request, String temperature, String weight) {
        ServerResponse checkLoginResult = iUserService.checkLoginStatus(request);
        if (checkLoginResult.isSuccess()) {
            User user = (User) checkLoginResult.getData();
            return iDetailService.createDetail(user.getUsername(), temperature, weight);
        }
        return checkLoginResult;
    }
}
