package com.honghu.springbootprovider;

import com.honghu.honghuspringbootstarter.annotation.EnableRpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务提供者启动类
 * @author hzd
 */
@SpringBootApplication
@EnableRpc
public class SpringBootProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProviderApplication.class, args);
    }

}
