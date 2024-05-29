package com.honghu.springbootconsumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 测试类
 * @author hzd
 */
@SpringBootTest
public class ExampleUserServiceImplTest {
    @Resource
    private ExampleUserServiceImpl service;
    @Test
    public void test(){
        service.test();
    }
}
