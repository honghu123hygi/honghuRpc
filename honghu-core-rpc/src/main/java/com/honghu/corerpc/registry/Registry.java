package com.honghu.corerpc.registry;

import com.honghu.corerpc.config.RegistryConfig;
import com.honghu.corerpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 注册中心
 *
 * @author hzd
 */
public interface Registry {
    /**
     * 注册服务
     * @param registryConfig
     */
    void init(RegistryConfig registryConfig);

    /**
     * 注册服务
     * @param serviceMetaInfo
     * @throws Exception
     */
    void register(ServiceMetaInfo serviceMetaInfo) throws Exception;
    /**
     * 注销服务
     * @param serviceMetaInfo
     */
    void unregister(ServiceMetaInfo serviceMetaInfo) throws ExecutionException, InterruptedException;
    /**
     * 服务发现
     * @param serviceKey
     * @return
     */
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);

    /**
     * 服务销毁
     */
    void destroy();

    /**
     * 心跳检测
     */
    void heartBeat();

    /**
     * 监听（消费端）
     * @param serviceNodeKey
     */
    void watch(String serviceNodeKey);
}
