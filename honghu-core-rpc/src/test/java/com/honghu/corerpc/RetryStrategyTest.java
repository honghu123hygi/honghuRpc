package com.honghu.corerpc;

import com.honghu.corerpc.fault.retry.NoRetryStrategy;
import com.honghu.corerpc.fault.retry.RetryStrategy;
import com.honghu.corerpc.model.RpcResponse;
import org.junit.Test;

/**
 * 重试策略测试
 */
public class RetryStrategyTest {
    RetryStrategy retryStrategy = new NoRetryStrategy();
    @Test
    public void testRetry() {
        try {
            RpcResponse rpcResponse = retryStrategy.deRetry(() -> {
                System.out.println("测试重试策略");
                throw new RuntimeException("模拟重试出现异常");
            });
            System.out.println(rpcResponse);
        }catch (Exception e){
            System.out.println("重试失败");
            e.printStackTrace();
        }
    }
}
