package com.honghu.springbootconsumer;

import com.honghu.honghuspringbootstarter.annotation.EnableRpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务消费者启动类
 * @author hzd
 */
@EnableRpc(needServer = false)
@SpringBootApplication
public class SpringBootConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsumerApplication.class, args);
    }

}
