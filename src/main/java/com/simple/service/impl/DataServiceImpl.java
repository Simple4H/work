package com.simple.service.impl;

import com.simple.common.ServerResponse;
import com.simple.dao.DataMapper;
import com.simple.pojo.Data;
import com.simple.service.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create By S I M P L E On 2018/07/31 15:52:56
 */
@Service("iDataService")
public class DataServiceImpl implements IDataService {

    @Autowired
    private DataMapper dataMapper;

    public ServerResponse createNewData(String number, String author) {
        Data data = new Data();
        data.setNumber(number);
        data.setPersonnel(author);
        int result = dataMapper.insert(data);
        if (result > 0){
            return ServerResponse.createBySuccessMessage("新建数据成功");
        }
        return ServerResponse.createByErrorMessage("新建数据异常");
    }

    public ServerResponse finishNewData(String number) {
        int result = dataMapper.updateFinishTime(number);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("结束成功");
        }
        return ServerResponse.createByErrorMessage("结束异常");
    }
}
