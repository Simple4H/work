package com.simple.dao;

import com.simple.pojo.Data;
import com.simple.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Data record);

    int insertSelective(Data record);

    Data selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Data record);

    int updateByPrimaryKey(Data record);

    int updateFinishTime(String number);

    List<User> getAllData(String username);

    Data selectByNumber(@Param(value = "number") String number, @Param(value = "username") String username);

    int checkNumber(@Param(value = "number") String number, @Param(value = "username") String username);
}