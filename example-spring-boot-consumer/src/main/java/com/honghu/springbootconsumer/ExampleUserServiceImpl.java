package com.honghu.springbootconsumer;

import com.honghu.common.model.User;
import com.honghu.common.service.UserService;
import com.honghu.honghuspringbootstarter.annotation.RpcReference;
import org.springframework.stereotype.Service;

/**
 * 远程调用测试类
 * @author hzd
 */
@Service
public class ExampleUserServiceImpl {
    @RpcReference
    private UserService userService;

    public void test(){
        User user = new User();
        user.setName("洪子舟");
        User newUser = userService.getUser(user);
        System.out.println(newUser.getName());
    }
}
