package com.honghu.corerpc.fault.tolerant;

import com.honghu.corerpc.spi.SpiLoader;

/**
 * 容错策略工厂
 * @author hzd
 */
public class TolerantStrategyFactory {
    static {
        SpiLoader.load(TolerantStrategy.class);
    }
    /**
     * 默认容错策略
     */
    private static final TolerantStrategy DEFAULT_TOLERANT_STRATEGY = new FailFastTolerantStrategy();

    /**
     * 获取实例
     * @param key
     * @return
     */
    public static TolerantStrategy getInstance(String key){

        return SpiLoader.getInstance(TolerantStrategy.class,key);
    }

}
