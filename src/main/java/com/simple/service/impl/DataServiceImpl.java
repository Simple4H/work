package com.simple.service.impl;

import com.simple.common.ServerResponse;
import com.simple.dao.DataMapper;
import com.simple.pojo.Data;
import com.simple.service.IDataService;
import com.simple.util.RedisPoolUtil;
import lombok.extern.slf4j.Slf4j;
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

    public ServerResponse createNewData(String number, String author) {
        Data data = new Data();
        data.setNumber(number);
        data.setPersonnel(author);
        int result = dataMapper.insert(data);
        if (result > 0){
            ServerResponse.createBySuccessMessage("新建数据成功");
            Data dataResult = dataMapper.selectByNumber(number);
            // 将数据缓存到Redis中
            try {
                RedisPoolUtil.set(author,String.valueOf(dataResult.getId()));
                return ServerResponse.createBySuccessMessage("新建任务成功");
            } catch (Exception e) {
                log.error("数据缓存到Redis中异常{}",e);
            }
        }
        return ServerResponse.createByErrorMessage("新建数据异常");
    }

    public ServerResponse finishNewData(String number,String author) {
        int result = dataMapper.updateFinishTime(number);
        if (result > 0) {
            // 删除Redis缓存
            try {
                RedisPoolUtil.del(author);
            } catch (Exception e) {
                log.error("删除Redis缓存失败",e);
            }
            return ServerResponse.createBySuccessMessage("结束成功");
        }
        return ServerResponse.createByErrorMessage("结束异常");
    }
}
