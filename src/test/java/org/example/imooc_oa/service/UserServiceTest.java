package org.example.imooc_oa.service;

import org.example.imooc_oa.Entity.User;
import org.junit.jupiter.api.Test;

class UserServiceTest {
    private UserService userService =new UserService();
    @Test
    void checkLogin1() {
        User user = userService.checkLogin("Test","test");
        System.out.println(user.getEmployeeId());
    }
    @Test
    void checkLogin2() {
        User user = userService.checkLogin("Test1","test");
        System.out.println(user.getEmployeeId());
    }
    @Test
    void checkLogin3() {
        User user = userService.checkLogin("Test","test1");
        System.out.println(user.getEmployeeId());
    }

}