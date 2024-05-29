package com.honghu.corerpc.fault.retry;

import com.honghu.corerpc.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * 不重试 - 重试策略
 * @author hzd
 */
public class NoRetryStrategy implements RetryStrategy{
    @Override
    public RpcResponse deRetry(Callable<RpcResponse> callable) throws Exception {
        return callable.call();
    }
}
