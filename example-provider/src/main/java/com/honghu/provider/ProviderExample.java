package com.honghu.provider;

import com.honghu.common.service.UserService;
import com.honghu.corerpc.bootstrap.ProviderBootStrap;
import com.honghu.corerpc.model.ServiceRegisterInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务提供者示例
 * @author hzd
 */
public class ProviderExample {
    public static void main(String[] args) {
        //要注册的服务
        List<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo serviceRegisterInfo = new ServiceRegisterInfo(UserService.class.getName(), UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);
        //服务提供者初始化
        ProviderBootStrap.init(serviceRegisterInfoList);
    }
}
