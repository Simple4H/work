package com.simple.controller;

import com.simple.common.ServerResponse;
import com.simple.pojo.User;
import com.simple.service.IDataService;
import com.simple.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Create By S I M P L E On 2018/07/31 15:50:59
 */
@Controller
@RequestMapping(value = "/data/")
@Slf4j
public class DataController {

    @Autowired
    private IDataService iDataService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "create_new_data.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse createNewData(String number, HttpServletRequest request) {
        ServerResponse checkLoginResult = iUserService.checkLoginStatus(request);
        if (checkLoginResult.isSuccess()) {
            User user = (User) checkLoginResult.getData();
            return iDataService.createNewData(number, user.getUsername());
        }
        return checkLoginResult;
    }

    @RequestMapping(value = "finish_new_data.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse finishNewData(HttpServletRequest request) {
        ServerResponse checkLoginResult = iUserService.checkLoginStatus(request);
        if (checkLoginResult.isSuccess()) {
            User user = (User) checkLoginResult.getData();
            return iDataService.finishNewData(user.getUsername());
        }
        return checkLoginResult;
    }

    // 查询用户的所有数据
    @RequestMapping(value = "get_all_data.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getAllData(HttpServletRequest request, int pageNum) {
        ServerResponse checkLoginResult = iUserService.checkLoginStatus(request);
        if (checkLoginResult.isSuccess()) {
            User user = (User) checkLoginResult.getData();
            return iDataService.getAllData(user.getUsername(), pageNum);
        }
        return checkLoginResult;
    }

    // 通过编号查询
    @RequestMapping(value = "select_by_number.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse selectByNumber(HttpServletRequest request, String number) {
        ServerResponse checkLoginResult = iUserService.checkLoginStatus(request);
        if (checkLoginResult.isSuccess()) {
            User user = (User) checkLoginResult.getData();
            return iDataService.selectByNumber(number, user.getUsername());
        }
        return checkLoginResult;
    }
}
