package com.simple.service.impl;

import com.simple.common.Const;
import com.simple.common.ResponseCode;
import com.simple.common.ServerResponse;
import com.simple.dao.UserMapper;
import com.simple.pojo.User;
import com.simple.service.IUserService;
import com.simple.util.CookieUtil;
import com.simple.util.JsonUtil;
import com.simple.util.MD5Util;
import com.simple.util.RedisShardedPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


/**
 * Create By S I M P L E On 2018/07/27 15:45:36
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    // 注册
    public ServerResponse register(User user) {
        ServerResponse checkResult = checkUsernameAndPassword(user.getUsername(), user.getEmail());
        if (checkResult.isSuccess()) {
            //设置MD5密码，权限，状态
            user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
            user.setAuthority(Const.Authority.AUTHORITY_CUSTOMER);
            user.setStatus(Const.Status.STATUS_UNCHECK);
            int registerResult = userMapper.insert(user);
            if (registerResult > 0) {
                return ServerResponse.createBySuccessMessage("注册成功");
            }
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return checkResult;
    }

    // 登录
    // TODO: 2018/7/30 用户名登录
    public ServerResponse<User> login(String email, String password) {
        int emailResult = userMapper.checkEmail(email);
        if (emailResult > 0) {
            User user = userMapper.emailLogin(email, MD5Util.MD5EncodeUtf8(password));
            if (user != null) {
                return ServerResponse.createBySuccess("登录成功", user);
            }
            return ServerResponse.createByErrorMessage("密码错误");
        }
        return ServerResponse.createByErrorMessage("邮箱不存在");
    }

    // 查看登录状态
    public ServerResponse checkLoginStatus(HttpServletRequest request) {
        String loginToken = CookieUtil.readLoginToken(request);
        User user = JsonUtil.string2Obj(RedisShardedPoolUtil.get(loginToken), User.class);
        if (user != null) {
            return ServerResponse.createBySuccess("获取成功", user);
        }
        return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getDesc());
    }

    // 验证权限
    public ServerResponse checkAdmin(int authority) {
        if (authority == Const.Authority.AUTHORITY_ADMIN) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorMessage("权限不足");
    }

    // 检查用户名和邮箱
    private ServerResponse checkUsernameAndPassword(String username, String email) {
        int usernameResult = userMapper.checkUsername(username);
        if (usernameResult > 0) {
            return ServerResponse.createByErrorMessage("用户名字已经存在");
        }
        int emailResult = userMapper.checkEmail(email);
        if (emailResult > 0) {
            return ServerResponse.createByErrorMessage("邮箱已经存在");
        }
        return ServerResponse.createBySuccess();
    }
}
