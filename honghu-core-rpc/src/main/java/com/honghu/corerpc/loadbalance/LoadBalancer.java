package com.honghu.corerpc.loadbalance;

import com.honghu.corerpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;

/**
 * 负载均衡器
 * @author hzd
 */
public interface LoadBalancer {

    /**
     * 选择服务调用
     * @param requestParams
     * @param serviceMetaInfoList
     * @return
     */
    ServiceMetaInfo select(Map<String,Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList);
}
