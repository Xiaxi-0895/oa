package org.example.imooc_oa.mapper;

import org.example.imooc_oa.Entity.User;
import org.example.imooc_oa.Utils.MybatisUtils;

public class UserMapper {
    public User SelectByUsername(String username) {
        return (User)MybatisUtils.executeSql(sqlSession -> sqlSession.selectOne("user.selectByUsername", username));
    }
}
