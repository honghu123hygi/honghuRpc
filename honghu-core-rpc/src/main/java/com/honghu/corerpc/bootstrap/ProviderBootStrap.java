package com.honghu.corerpc.bootstrap;

import com.honghu.corerpc.RpcApplication;
import com.honghu.corerpc.config.RegistryConfig;
import com.honghu.corerpc.config.RpcConfig;
import com.honghu.corerpc.model.ServiceRegisterInfo;
import com.honghu.corerpc.model.ServiceMetaInfo;
import com.honghu.corerpc.registry.LocalRegistry;
import com.honghu.corerpc.registry.Registry;
import com.honghu.corerpc.registry.RegistryFactory;
import com.honghu.corerpc.server.tcp.VertxTcpServer;

import java.util.List;

/**
 * 服务提供者初始化
 * @author hzd
 */
public class ProviderBootStrap {

    /**
     * 初始化
     */
    public static void init(List<ServiceRegisterInfo<?>> serviceRegisterInfoList) {
        // RPC 框架初始化（配置和注册中心）
        RpcApplication.init();
        // 全局配置
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        // 注册服务
        for (ServiceRegisterInfo<?> serviceRegisterInfo : serviceRegisterInfoList) {
            String serviceName = serviceRegisterInfo.getServiceName();
            // 本地注册
            LocalRegistry.registry(serviceName, serviceRegisterInfo.getImplClass());

            // 注册服务到注册中心
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
            try {
                registry.register(serviceMetaInfo);
            } catch (Exception e) {
                throw new RuntimeException(serviceName + " 服务注册失败", e);
            }
        }

        // 启动服务器
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(rpcConfig.getServerPort());
    }
}
