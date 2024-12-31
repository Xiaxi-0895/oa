package org.example.imooc_oa.mapper;

import org.example.imooc_oa.Entity.Node;
import org.example.imooc_oa.Utils.MybatisUtils;

import java.util.List;

public class RbacMapper {
    public List<Node> selectNodeByUserId(long userId) {
        return (List<Node>) MybatisUtils.executeSql(sqlSession -> sqlSession.selectList("rbac.selectNodeByUserId",userId));
    }
}
