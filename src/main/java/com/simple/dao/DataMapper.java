package com.simple.dao;

import com.simple.pojo.Data;

public interface DataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Data record);

    int insertSelective(Data record);

    Data selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Data record);

    int updateByPrimaryKey(Data record);

    int updateFinishTime(String number);

    Data selectByNumber(String number);
}