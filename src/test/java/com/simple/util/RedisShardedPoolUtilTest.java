package com.simple.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Create By S I M P L E On 2018/12/20 10:53:20
 */

@Slf4j
public class RedisShardedPoolUtilTest {

    @Test
    public void set() {
        for (int i = 0; i < 10; i++) {
            RedisShardedPoolUtil.set("key" + i, "value" + i);
        }
    }

    @Test
    public void get() {
        for (int i = 0; i < 10; i++) {
            String result = RedisShardedPoolUtil.get("key" + i);
            log.info("key is :{},value:{}", "key" + i, result);
        }
    }

    @Test
    public void del() {
        for (int i = 0; i < 10; i++) {
            String result = RedisShardedPoolUtil.get("key" + i);
            log.info("key is :{},value:{}", "key" + i, result);
        }

        for (int i = 0; i < 10; i++) {
            RedisShardedPoolUtil.del("key" + i);
        }
        log.info("================= delete all keys =====================");

        for (int i = 0; i < 10; i++) {
            String result = RedisShardedPoolUtil.get("key" + i);
            log.info("key is :{},value:{}", "key" + i, result);
        }
    }


}