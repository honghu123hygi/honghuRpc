package com.honghu.provider;

import com.honghu.common.model.User;
import com.honghu.common.service.UserService;

/**
 * @author hzd
 */
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(User user) {
        System.out.println("用户名为：" + user.getName());
        return user;
    }

    @Override
    public boolean check(User user) {
        return user.getName().equals("洪子舟");
    }


}
