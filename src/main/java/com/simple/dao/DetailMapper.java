package com.simple.dao;

import com.simple.pojo.Detail;

public interface DetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Detail record);

    int insertSelective(Detail record);

    Detail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Detail record);

    int updateByPrimaryKey(Detail record);
}