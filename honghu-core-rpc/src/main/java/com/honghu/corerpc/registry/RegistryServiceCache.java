package com.honghu.corerpc.registry;

import com.honghu.corerpc.model.ServiceMetaInfo;

import java.util.List;

/**
 * 注册中心服务本地缓存
 * @author hzd
 */
public class RegistryServiceCache {

    /**
     * 服务缓存
     */
    List<ServiceMetaInfo> serviceCache;

    /**
     * 写缓存
     * @param newServiceCache
     */
    void writeCache(List<ServiceMetaInfo> newServiceCache){
        this.serviceCache = newServiceCache;
    }

    /**
     * 读缓存
     * @return
     */
    List<ServiceMetaInfo> readCache(){
        return serviceCache;
    }

    /**
     * 清空缓存
     */
    void clearCache(){
        this.serviceCache = null;
    }
}
