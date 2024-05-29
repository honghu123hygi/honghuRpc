package com.honghu.common.service;

import com.honghu.common.model.User;

/**
 * @author hzd
 */
public interface UserService {

    /**
     * 获取用户
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * 校验
     * @param user
     * @return
     */
    boolean check(User user);

    /**
     * 测试，获取数字
     * @return
     */
    default short getNum(){
        return 1;
    }
}
