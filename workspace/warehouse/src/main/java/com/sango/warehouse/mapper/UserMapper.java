package com.sango.warehouse.mapper;

import com.sango.warehouse.entity.User;

/*
    user_info表的mapper 接口
 */
public interface UserMapper {

    //根据账号查询用户信息的方法
    public User findUserByCode(String userCode);
}
