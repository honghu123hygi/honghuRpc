package com.honghu.honghuspringbootstarter.bootstrap;

import com.honghu.corerpc.RpcApplication;
import com.honghu.corerpc.config.RpcConfig;
import com.honghu.corerpc.server.tcp.VertxTcpServer;
import com.honghu.honghuspringbootstarter.annotation.EnableRpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Rpc 框架启动类
 * @author hzd
 */
@Slf4j
public class RpcInitBootStrap implements ImportBeanDefinitionRegistrar {
    /**
     * Spring 初始化时执行，初始化 RPC 框架
     * @param importingClassMetadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry){
        boolean needServer = (boolean) importingClassMetadata.getAnnotationAttributes(EnableRpc.class.getName()).get("needServer");
        //RPC 框架初始化
        RpcApplication.init();
        //全局配置
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        //启动服务器
        if(needServer){
            VertxTcpServer vertxTcpServer = new VertxTcpServer();
            vertxTcpServer.doStart(rpcConfig.getServerPort());
        }else {
            log.info("不启动 server");
        }
    }
}
