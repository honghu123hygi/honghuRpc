package com.honghu.easyrpc.server;

/**
 * Http服务器接口
 *
 * @author hzd
 */
public interface HttpServer {
    /**
     * 启动服务器
     * @param port
     */
    void doStart(int port);
}
