package com.simple.test;

import com.simple.pojo.Data;
import com.simple.pojo.User;
import com.simple.util.JsonUtil;
import com.simple.util.RedisShardedPoolUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By S I M P L E On 2019/01/08 21:25:51
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        User user1 = new User();
        user1.setUsername("真的不行吗");
        User user2 = new User();
        user2.setUsername("咋回事啊");
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        RedisShardedPoolUtil.set("userList", JsonUtil.obj2String(list));
        List<User> result = (List<User>) JsonUtil.string2Obj(RedisShardedPoolUtil.get("userList"),List.class);
        log.info("查询第一个:{}",result.get(0));
        log.info("查询第一个:{}",result.get(1));


    }

}
