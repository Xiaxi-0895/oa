package org.example.imooc_oa.service;


import org.example.imooc_oa.Entity.User;
import org.example.imooc_oa.Utils.Md5Utils;
import org.example.imooc_oa.mapper.UserMapper;
import org.example.imooc_oa.service.error.LoginError;

public class UserService {
    UserMapper userMapper = new UserMapper();
    public User checkLogin(String username, String password) {
        User user = userMapper.SelectByUsername(username);
        if (user == null) {
            throw new LoginError("用户名不存在");
        }else if (!Md5Utils.md5Digest(password,user.getSalt()).equals(user.getPassword())) {
            throw new LoginError("密码错误");
        }else{
            return user;
        }
    }
}
