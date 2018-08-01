package com.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WorkApplicationTests {

    @Test
    public void contextLoads() {
        String a = "simple" + "times";
        log.error("a:{}",a);

    }

}
