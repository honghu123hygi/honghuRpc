package com.honghu.corerpc.fault.tolerant;

import com.honghu.corerpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 降级到其他异常
 * @author hzd
 */
@Slf4j
public class FailBackTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        //TODO 可自行扩展，获得降级的服务并调用
        return null;
    }
}
