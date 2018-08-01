package com.simple.service.impl;

import com.simple.common.ServerResponse;
import com.simple.dao.DetailMapper;
import com.simple.pojo.Detail;
import com.simple.service.IDetailService;
import com.simple.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Create By S I M P L E On 2018/08/01 15:05:04
 */
@Service("iDetailService")
public class DetailServiceImpl implements IDetailService {

    @Autowired
    private DetailMapper detailMapper;

    public ServerResponse createDetail(String author, String temperature, String weight) {
        Detail detail = new Detail();
        // 获取编号
        String number = RedisPoolUtil.get(author);
        if (StringUtils.isEmpty(number)) {
            return ServerResponse.createByErrorMessage("获取编号为空");
        }
        // 设置次数
        String timesResult = RedisPoolUtil.get(author + "times");
        // 判断是否第一次
        if (StringUtils.isEmpty(timesResult)) {
            RedisPoolUtil.set(author + "times", "0");
        }
        int times = Objects.requireNonNull(RedisPoolUtil.incr(author + "times")).intValue();
        // 设置数据
        detail.setDataNumber(number);
        detail.setTimes(times);
        detail.setTemperature(temperature);
        detail.setWeight(weight);
        int result = detailMapper.insert(detail);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("记录成功");
        }
        return ServerResponse.createByErrorMessage("记录失败");
    }
}
