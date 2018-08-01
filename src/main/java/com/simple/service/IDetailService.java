package com.simple.service;

import com.simple.common.ServerResponse;

/**
 * Create By S I M P L E On 2018/08/01 15:04:45
 */
public interface IDetailService {

    ServerResponse createDetail(String author, String temperature, String weight);
}
