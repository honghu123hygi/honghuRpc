package com.honghu.corerpc;

import cn.hutool.core.util.IdUtil;
import com.honghu.corerpc.constant.RpcConstant;
import com.honghu.corerpc.model.RpcRequest;
import com.honghu.corerpc.protocol.*;
import com.honghu.corerpc.serializer.JdkSerializer;
import com.honghu.corerpc.serializer.Serializer;
import io.vertx.core.buffer.Buffer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ProtocolMessageTest {
    @Test
    public void testSerializer() throws IOException {
        Serializer serializer = new JdkSerializer();
        RpcRequest request = new RpcRequest();
        request.setServiceName("myService");
        request.setMethodName("myMethod");
        request.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
        request.setParameterTypes(new Class[]{String.class});
        request.setArgs(new Object[]{"aaa","bbb"});
        byte[] bytes = serializer.serialize(request);
        System.out.println(bytes);
        RpcRequest rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
        Assert.assertNotNull(rpcRequest);
    }

    @Test
    public void testEncodeAndDecode() throws IOException {
        //构造消息
        ProtocolMessage<RpcRequest> message = new ProtocolMessage<>();
        ProtocolMessage.Header header = new ProtocolMessage.Header();
        header.setMagic(ProtocolConstant.PROTOCOL_MAGIC);
        header.setVersion(ProtocolConstant.PROTOCOL_VERSION);
        header.setSerializer((byte) ProtocolMessageSerializerEnum.JDK.getKey());
        header.setType((byte) ProtocolMessageTypeEnum.REQUEST.getKey());
        header.setStatus((byte) ProtocolMessageStatusEnum.OK.getValue());
        header.setRequestId(IdUtil.getSnowflakeNextId());
        header.setBodyLength(0);

        RpcRequest request = new RpcRequest();
        request.setServiceName("myService");
        request.setMethodName("myMethod");
        request.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
        request.setParameterTypes(new Class[]{String.class});
        request.setArgs(new Object[]{"aaa","bbb"});

        message.setHeader(header);
        message.setBody(request);
        Buffer encodeBuffer = ProtocolMessageEncoder.encoder(message);
        ProtocolMessage<?> protocolMessage = ProtocolMessageDecoder.decoder(encodeBuffer);
        Assert.assertNotNull(protocolMessage);
    }

}
