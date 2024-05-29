package com.honghu.corerpc.server.tcp;

import cn.hutool.core.util.IdUtil;
import com.honghu.corerpc.RpcApplication;
import com.honghu.corerpc.model.RpcRequest;
import com.honghu.corerpc.model.RpcResponse;
import com.honghu.corerpc.model.ServiceMetaInfo;
import com.honghu.corerpc.protocol.*;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


/**
 * TCP 客户端
 * Vertx TCP 请求客户端
 * @author hzd
 */
public class VertxTcpClient {

    public static RpcResponse doRequest(RpcRequest rpcRequest, ServiceMetaInfo serviceMetaInfo) throws ExecutionException, InterruptedException {
        //发送 TCP 请求
        Vertx vertx = Vertx.vertx();
        NetClient netClient = vertx.createNetClient();
        CompletableFuture<RpcResponse> responseFuture = new CompletableFuture<>();
        netClient.connect(serviceMetaInfo.getServicePort(),serviceMetaInfo.getServiceHost(),
                result -> {
                    if(!result.succeeded()){
                        System.err.println("Failed to connect TCP server.");
                        return;
                    }
                    NetSocket netSocket = result.result();
                    //发送数据
                    //构造消息
                    ProtocolMessage<RpcRequest> protocolMessage = new ProtocolMessage<>();
                    ProtocolMessage.Header header = new ProtocolMessage.Header();
                    header.setMagic(ProtocolConstant.PROTOCOL_MAGIC);
                    header.setVersion(ProtocolConstant.PROTOCOL_VERSION);
                    header.setSerializer((byte) ProtocolMessageSerializerEnum.getEnumByValue(RpcApplication.getRpcConfig().getSerializer()).getKey());
                    header.setType((byte) ProtocolMessageTypeEnum.REQUEST.getKey());
                    //生成全局Id
                    header.setRequestId(IdUtil.getSnowflakeNextId());
                    protocolMessage.setHeader(header);
                    protocolMessage.setBody(rpcRequest);

                    //编码请求
                    try {
                        Buffer bufferEncode = ProtocolMessageEncoder.encoder(protocolMessage);
                        netSocket.write(bufferEncode);
                    }catch (IOException e){
                        throw new RuntimeException("协议消息编码错误");
                    }

                    //接受响应
                    TcpHandlerBufferWrapper bufferWrapper = new TcpHandlerBufferWrapper(
                            buffer -> {
                                try {
                                    ProtocolMessage<RpcResponse> rpcResponseProtocolMessage =
                                            (ProtocolMessage<RpcResponse>) ProtocolMessageDecoder.decoder(buffer);
                                    responseFuture.complete(rpcResponseProtocolMessage.getBody());
                                }catch (IOException e){
                                    throw new RuntimeException("协议消息解码错误");
                                }
                            }
                    );
                    netSocket.handler(bufferWrapper);
                });
        RpcResponse rpcResponse = responseFuture.get();
        //关闭链接
        netClient.close();
        return rpcResponse;
    }
}