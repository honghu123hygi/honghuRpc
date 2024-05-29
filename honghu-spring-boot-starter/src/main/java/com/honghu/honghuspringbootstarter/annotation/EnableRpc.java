package com.honghu.honghuspringbootstarter.annotation;

import com.honghu.honghuspringbootstarter.bootstrap.RpcConsumerBootStrap;
import com.honghu.honghuspringbootstarter.bootstrap.RpcInitBootStrap;
import com.honghu.honghuspringbootstarter.bootstrap.RpcProviderBootStrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启动 RPC 注解
 * @author hzd
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootStrap.class, RpcConsumerBootStrap.class, RpcProviderBootStrap.class})
public @interface EnableRpc {

    /**
     * 需要启动 server
     * @return
     */
    boolean needServer() default true;
}
