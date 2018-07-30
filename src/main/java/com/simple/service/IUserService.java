package com.simple.service;


import com.simple.common.ServerResponse;
import com.simple.pojo.User;

/**
 * Create By S I M P L E On 2018/07/27 15:45:10
 */
public interface IUserService {

    ServerResponse register(User user);

    ServerResponse<User> login(String email, String password);

}
