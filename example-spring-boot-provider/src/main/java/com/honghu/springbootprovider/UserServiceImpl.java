package com.honghu.springbootprovider;

import com.honghu.common.model.User;
import com.honghu.common.service.UserService;
import com.honghu.honghuspringbootstarter.annotation.RpcService;
import org.springframework.stereotype.Service;

/**
 * UserService实现类
 * @author hzd
 */
@Service
@RpcService
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(User user) {
        System.out.println(user.getName());
        return user;
    }

    @Override
    public boolean check(User user) {
        return user.equals("洪子舟");
    }
}
