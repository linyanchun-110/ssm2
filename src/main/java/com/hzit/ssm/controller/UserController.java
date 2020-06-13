package com.hzit.ssm.controller;

import com.hzit.ssm.model.User;
import com.hzit.ssm.req.UserAddReq;
import com.hzit.ssm.resp.*;
import com.hzit.ssm.service.crudUser;
import com.mysql.jdbc.StringUtils;
import net.sf.cglib.core.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private crudUser crudUser;
    private static final Logger logger= LoggerFactory.getLogger(UserController.class);
    @RequestMapping("/show")
    public String show(){
        logger.info("加载user页面");
        return "user";
    }
    @ResponseBody
    @RequestMapping("/add")
    public UserAddResp add(@RequestBody UserAddReq userAddReq){
        logger.info("请求参数:account:{},password:{},name:{},msg:{}",userAddReq.getAccount(),
                userAddReq.getPassword(),userAddReq.getName(),userAddReq.getInfo());
          System.out.println("-------添加用户---------");
          UserAddResp userAddResp=new UserAddResp();
         if(StringUtils.isNullOrEmpty(userAddReq.getAccount())){
            userAddResp.setStatus(-1);
            userAddResp.setMsg("账号不能为空");
            return userAddResp;
         }
         boolean flag=crudUser.addUser(userAddReq);
         if(flag==true){
             userAddResp.setStatus(1);
             userAddResp.setMsg("添加成功");
         }else {
             userAddResp.setStatus(-1);
             userAddResp.setMsg("添加失败，请重试");
         }
         logger.info("返回前端的数据:{}",userAddResp);
          return userAddResp;
    }
    @ResponseBody
    @RequestMapping("/queryAll")
    public UserQueryResp queryAll(){
        logger.info("接收到查询所有用户请求");
        UserQueryResp userQueryResp=new UserQueryResp();
        //查询到的所有用户
        List<User> userList=crudUser.queryAll();
        userQueryResp.setCode(0);
        userQueryResp.setMsg("查询成功");

        List<UserQueryData> dataList=new ArrayList<UserQueryData>();
        for(User u:userList){
            UserQueryData data=new UserQueryData();
            data.setId(u.getId());
            data.setAccount(u.getAccount());
            data.setPassword(u.getPassword());
            data.setName(u.getName());
            data.setInfo(u.getInfo());
            dataList.add(data);
        }
        userQueryResp.setData(dataList);
      return userQueryResp;
    }
    @ResponseBody
    @RequestMapping(value = "/userDelete/{id}",method = RequestMethod.GET)
    public UserDeleteResp userDelete(@PathVariable("id") Integer id){
        logger.info("接收到删除用户请求：id{}",id);
        UserDeleteResp userDeleteResp=new UserDeleteResp();
       boolean flag=crudUser.deleteUser(id);
       if(flag==true){
           userDeleteResp.setStatus(1);
           userDeleteResp.setMsg("删除成功");
       }else{
           userDeleteResp.setStatus(-1);
           userDeleteResp.setMsg("删除失败");
       }
       logger.info("返回前端的数据是:{}",userDeleteResp);
        return userDeleteResp;
    }
    @ResponseBody
    @RequestMapping("/userUpdate")
    public UserUpdateResp userUpdate(@RequestBody UserAddReq userAddReq){
        logger.info("请求参数:account:{},password:{},name:{},msg:{}",userAddReq.getAccount(), userAddReq.getPassword(),userAddReq.getName(),userAddReq.getInfo());
        UserUpdateResp userUpdateResp=new UserUpdateResp();
        boolean flag=crudUser.updateUser(userAddReq);
        if(flag==true){
            userUpdateResp.setStatus(1);
            userUpdateResp.setMsg("更新成功");
        }else{
            userUpdateResp.setStatus(-1);
            userUpdateResp.setMsg("更新失败");
        }
    logger.info("返回给前端的数据:{}",userUpdateResp);
        return userUpdateResp;
    }
}
