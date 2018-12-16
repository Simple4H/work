package com.simple.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simple.common.ServerResponse;
import com.simple.dao.DataMapper;
import com.simple.pojo.Data;
import com.simple.pojo.User;
import com.simple.service.IDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create By S I M P L E On 2018/07/31 15:52:56
 */
@Service("iDataService")
@Slf4j
public class DataServiceImpl implements IDataService {

    @Autowired
    private DataMapper dataMapper;

    public ServerResponse createNewData(String number, String username) {
        // 查看编号是否存在
        if (dataMapper.checkNumber(number, username) > 0) {
            return ServerResponse.createByErrorMessage("编号已经存在");
        }
        Data data = new Data();
        data.setNumber(number);
        data.setPersonnel(username);
        int result = dataMapper.insert(data);
        if (result > 0) {
            ServerResponse.createBySuccessMessage("新建数据成功");
            // 将数据缓存到Redis中
            try {
                RedisPoolUtil.set(username, number);
                return ServerResponse.createBySuccessMessage("新建任务成功");
            } catch (Exception e) {
                log.error("数据缓存到Redis中异常{}", e);
            }
        }
        return ServerResponse.createByErrorMessage("新建数据异常");
    }

    // 结束数据
    public ServerResponse finishNewData(String username) {
        // 获取缓存里面的编号
        String number = RedisPoolUtil.get(username);
        if (StringUtils.isEmpty(number)) {
            return ServerResponse.createByErrorMessage("缓存不存在");
        }
        // 更新结束时间
        int result = dataMapper.updateFinishTime(number);
        if (result > 0) {
            // 删除Redis缓存
            try {
                // 删除编号的缓存
                RedisPoolUtil.del(username);
                // 删除次数的缓存
                RedisPoolUtil.del(username + "times");
            } catch (Exception e) {
                log.error("删除Redis缓存失败", e);
            }
            return ServerResponse.createBySuccessMessage("结束成功");
        }
        return ServerResponse.createByErrorMessage("结束异常");
    }

    // 查询所有的文章
    public ServerResponse getAllData(String username, int pageNum) {
        // 分页
        PageHelper.startPage(pageNum, 3);
        List<User> userList = dataMapper.getAllData(username);
        if (userList.isEmpty()) {
            return ServerResponse.createByErrorMessage("没有查询到任何信息");
        }
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return ServerResponse.createBySuccess("查询到相关数据", pageInfo);
    }

    // 通过编号查询
    public ServerResponse selectByNumber(String number, String username) {
        Data data = dataMapper.selectByNumber(number, username);
        if (data != null) {
            return ServerResponse.createBySuccess("查询成功", data);
        }
        return ServerResponse.createByErrorMessage("没有查询到相关数据");
    }
}
