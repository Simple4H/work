package com.simple.service;

import com.simple.common.ServerResponse;

/**
 * Create By S I M P L E On 2018/07/31 15:52:40
 */
public interface IDataService {

    ServerResponse createNewData(String number, String author);

    ServerResponse finishNewData(String number);
}
