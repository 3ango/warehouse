package com.sango.warehouse.service;

import com.sango.warehouse.entity.User;

/*
    user_info表的service接口：
 */
public interface UserService {
    //根据账号查询用户的业务方法
    public User queryUserByCode(String userCode);

}
