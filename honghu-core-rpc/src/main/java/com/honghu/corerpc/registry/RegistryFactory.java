package com.honghu.corerpc.registry;

import com.honghu.corerpc.spi.SpiLoader;

/**
 * 注册中心工厂（用于获取注册中心对象）
 * @author hzd
 */
public class RegistryFactory {
    static {
        SpiLoader.load(Registry.class);
    }

    /**
     * 默认注册中心
     */
    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();

    /**
     * 获取注册中心实例
     * @param key
     * @return
     */
    public static Registry getInstance(String key){
        return SpiLoader.getInstance(Registry.class,key);
    }
}
