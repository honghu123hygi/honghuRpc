package com.honghu.provider;

import com.honghu.common.service.UserService;
import com.honghu.corerpc.RpcApplication;
import com.honghu.corerpc.config.RegistryConfig;
import com.honghu.corerpc.config.RpcConfig;
import com.honghu.corerpc.model.ServiceMetaInfo;
import com.honghu.corerpc.registry.LocalRegistry;
import com.honghu.corerpc.registry.Registry;
import com.honghu.corerpc.registry.RegistryFactory;
import com.honghu.corerpc.server.HttpServer;
import com.honghu.corerpc.server.VertxHttpServer;
import com.honghu.corerpc.server.tcp.VertxTcpServer;

/**
 * 简易服务提供者示例
 *
 * @author hzd
 */
public class EasyProviderExample {
    public static void main(String[] args) {
        //框架初始化
        RpcApplication.init();

        //注册服务
        String servicesName = UserService.class.getName();
        LocalRegistry.registry(servicesName,UserServiceImpl.class);

        //注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(servicesName);
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        try{
            registry.register(serviceMetaInfo);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        //启动 web 服务
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(80);
//        HttpServer httpServer = new VertxHttpServer();
//        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
