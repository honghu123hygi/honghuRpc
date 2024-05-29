package com.honghu.corerpc.protocol;


import com.honghu.corerpc.serializer.JdkSerializer;
import com.honghu.corerpc.serializer.Serializer;
import com.honghu.corerpc.serializer.SerializerFactory;
import io.vertx.core.buffer.Buffer;

import java.io.IOException;

/**
 * @author hzd
 */
public class ProtocolMessageEncoder {

    /**
     * 编码
     * @param protocolMessage
     * @return
     * @throws IOException
     */
    public static Buffer encoder(ProtocolMessage<?> protocolMessage) throws IOException {
        if(protocolMessage == null||protocolMessage.getHeader() == null){
            return Buffer.buffer();
        }else {
            ProtocolMessage.Header header = protocolMessage.getHeader();
            //依次向缓冲区写入字节
            Buffer buffer = Buffer.buffer();
            buffer.appendByte(header.getMagic());
            buffer.appendByte(header.getVersion());
            buffer.appendByte(header.getSerializer());
            buffer.appendByte(header.getType());
            buffer.appendByte(header.getStatus());
            buffer.appendLong(header.getRequestId());
            //获得序列化器
            ProtocolMessageSerializerEnum serializerEnum = ProtocolMessageSerializerEnum.getEnumByKey(header.getSerializer());
            if(serializerEnum == null){
                throw new RuntimeException("序列化协议不存在");
            }
            Serializer serializer = SerializerFactory.getInstance(serializerEnum.getValue());
            byte[] bodyByte = serializer.serialize(protocolMessage.getBody());
            //写入 body 长度和数据
            buffer.appendInt(bodyByte.length);
            buffer.appendBytes(bodyByte);
            return buffer;
        }
    }
}
