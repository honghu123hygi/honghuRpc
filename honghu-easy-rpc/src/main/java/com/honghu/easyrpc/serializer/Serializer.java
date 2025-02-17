package com.honghu.easyrpc.serializer;

import java.io.IOException;

/**
 * 序列化接口
 *
 * @author hzd
 */
public interface Serializer {

    /**
     * 序列化
     * @param object
     * @return
     * @param <T>
     * @throws IOException
     */
    <T> byte[] serializer(T object) throws IOException;

    /**
     * 反序列化
     * @param bytes
     * @param type
     * @return
     * @param <T>
     * @throws IOException
     */
    <T> T deserializer(byte[] bytes,Class<T> type) throws IOException;
}
