//package com.honghu.consumer;
//
//import cn.hutool.http.HttpRequest;
//import cn.hutool.http.HttpResponse;
//import com.honghu.common.model.User;
//import com.honghu.common.service.UserService;
//import com.honghu.easyrpc.model.RpcRequest;
//import com.honghu.easyrpc.model.RpcResponse;
//import com.honghu.easyrpc.serializer.JdkSerializer;
//import com.honghu.easyrpc.serializer.Serializer;
//import io.vertx.core.http.HttpServerResponse;
//
//import java.io.IOException;
//
//
///**
// * 静态代理
// * @author hzd
// */
//public class UserServiceProxy implements UserService {
//    @Override
//    public User getUser(User user) {
//        //指定序列化器
//        Serializer serializer = new JdkSerializer();
//
//        //发请求
//        RpcRequest rpcRequest = RpcRequest.builder()
//                .serviceName(UserService.class.getName())
//                .methodName("getUser")
//                .parameterTypes(new Class[]{User.class})
//                .args(new Object[]{user})
//                .build();
//        try{
//            byte[] bytes = serializer.serializer(rpcRequest);
//            byte[] result;
//            try(HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
//                    .body(bytes)
//                    .execute()){
//                result = httpResponse.bodyBytes();
//            }
//            RpcResponse rpcResponse = serializer.deserializer(result, RpcResponse.class);
//            return (User)rpcResponse.getData();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public boolean check(User user) {
//        return false;
//    }
//}
