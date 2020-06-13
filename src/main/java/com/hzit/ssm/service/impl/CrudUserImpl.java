package com.hzit.ssm.service.impl;

import com.hzit.ssm.mapper.UserMapper;
import com.hzit.ssm.model.User;
import com.hzit.ssm.req.UserAddReq;
import com.hzit.ssm.service.crudUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrudUserImpl implements crudUser {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addUser(UserAddReq userAddReq) {
          User user=new User();
          user.setAccount(userAddReq.getAccount());
          user.setPassword(userAddReq.getPassword());
          user.setName(userAddReq.getName());
          user.setInfo(userAddReq.getInfo());
          int rlt=userMapper.insert(user);
          if(rlt>0){
            return true;
          }else{
            return false;
        }
    }
    @Override
    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    @Override
    public boolean deleteUser(Integer id) {
       int rlt=userMapper.deleteByPrimaryKey(id);
        if(rlt>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateUser(UserAddReq userAddReq) {
        User user=new User();
        user.setId(userAddReq.getId());
        user.setAccount(userAddReq.getAccount());
        user.setPassword(userAddReq.getPassword());
        user.setName(userAddReq.getName());
        user.setInfo(userAddReq.getInfo());
        int rlt=userMapper.updateByPrimaryKeySelective(user);
        if(rlt>0){
            return true;
        }else{
            return  false;
        }
    }
}
