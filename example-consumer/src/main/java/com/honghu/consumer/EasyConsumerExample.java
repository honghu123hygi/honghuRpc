package com.honghu.consumer;

import com.honghu.common.model.User;
import com.honghu.common.service.UserService;
import com.honghu.corerpc.proxy.ServiceProxyFactory;

/**
 * 简易服务消费者实例
 *
 * @author hzd
 */
public class EasyConsumerExample {
    public static void main(String[] args) {

        //动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("洪子舟");

        User newUser = userService.getUser(user);
        if(newUser != null){
            System.out.println(newUser.getName());
        }else{
            System.out.println("user == null");
        }
//        int number = userService.getNum();
//        System.out.println(number);
    }
}
