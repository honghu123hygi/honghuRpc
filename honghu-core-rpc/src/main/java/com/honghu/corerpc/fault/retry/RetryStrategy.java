package com.honghu.corerpc.fault.retry;

import com.honghu.corerpc.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * 重试策略
 * @author hzd
 */
public interface RetryStrategy {

    /**
     * 重试
     * @param callable
     * @return
     * @throws Exception
     */
    RpcResponse deRetry(Callable<RpcResponse> callable) throws Exception;
}
