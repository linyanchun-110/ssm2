package com.hzit.ssm.service;

import com.hzit.ssm.model.User;
import com.hzit.ssm.req.UserAddReq;

import java.util.List;

public interface crudUser {
    public boolean addUser(UserAddReq userAddReq);
    public List<User> queryAll();
    public boolean deleteUser(Integer id);
    public boolean updateUser(UserAddReq userAddReq);
}
