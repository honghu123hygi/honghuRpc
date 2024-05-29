package com.honghu.corerpc.fault.tolerant;

import com.honghu.corerpc.model.RpcResponse;

import java.util.Map;

/**
 * 转移到其他服务节点
 * @author hzd
 */
public class FailOverTolerantStrategy implements TolerantStrategy{

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        //TODO 可自行扩展，获得其他服务节点并调用
        return null;
    }
}
