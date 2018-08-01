package com.simple.service.impl;

import com.simple.common.ServerResponse;
import com.simple.dao.DataMapper;
import com.simple.pojo.Data;
import com.simple.service.IDataService;
import com.simple.util.RedisPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create By S I M P L E On 2018/07/31 15:52:56
 */
@Service("iDataService")
@Slf4j
public class DataServiceImpl implements IDataService {

    @Autowired
    private DataMapper dataMapper;

    // TODO: 2018/8/1 编号重复
    public ServerResponse createNewData(String number, String author) {
        Data data = new Data();
        data.setNumber(number);
        data.setPersonnel(author);
        int result = dataMapper.insert(data);
        if (result > 0){
            ServerResponse.createBySuccessMessage("新建数据成功");
            // 将数据缓存到Redis中
            try {
                RedisPoolUtil.set(author,number);
                return ServerResponse.createBySuccessMessage("新建任务成功");
            } catch (Exception e) {
                log.error("数据缓存到Redis中异常{}",e);
            }
        }
        return ServerResponse.createByErrorMessage("新建数据异常");
    }

    public ServerResponse finishNewData(String author) {
        // 获取缓存里面的编号
        String number = RedisPoolUtil.get(author);
        if (StringUtils.isEmpty(number)){
            return ServerResponse.createByErrorMessage("缓存不存在");
        }
        // 更新结束时间
        int result = dataMapper.updateFinishTime(number);
        if (result > 0) {
            // 删除Redis缓存
            try {
                // 删除编号的缓存
                RedisPoolUtil.del(author);
                // 删除次数的缓存
                RedisPoolUtil.del(author+"times");
            } catch (Exception e) {
                log.error("删除Redis缓存失败",e);
            }
            return ServerResponse.createBySuccessMessage("结束成功");
        }
        return ServerResponse.createByErrorMessage("结束异常");
    }
}
