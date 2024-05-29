package com.honghu.corerpc.bootstrap;

import com.honghu.corerpc.RpcApplication;

/**
 * 服务消费者初始化
 * @author hzd
 */
public class ConsumerBootStrap {
    /**
     * 初始化
     */
    public static void init(){
        //RPC 框架初始化（配置和注册中心）
        RpcApplication.init();
    }
}
