package com.simple.common;

/**
 * Create By S I M P L E On 2018/07/28 14:07:45
 */
public class Const {

    public interface Authority {
        int AUTHORITY_ADMIN = 10; // 管理员
        int AUTHORITY_CUSTOMER = 0; // 普通用户
    }

    public interface Status {
        int STATUS_CHECK = 0; // 未校验
        int STATUS_UNCHECK = 1; // 已校验
    }

    public interface RedisTime {
        int REDIS_CACHE_EXTIME = 60 * 60 * 24 * 3;
    }
}
