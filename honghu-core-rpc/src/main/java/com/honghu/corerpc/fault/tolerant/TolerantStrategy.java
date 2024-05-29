package com.honghu.corerpc.fault.tolerant;

import com.honghu.corerpc.model.RpcResponse;

import java.util.Map;

/**
 * 容错策略
 * @author hzd
 */
public interface TolerantStrategy {

    /**
     * 容错
     * @param context
     * @param e
     * @return
     */
    RpcResponse doTolerant(Map<String,Object> context,Exception e);
}
