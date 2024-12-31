package org.example.imooc_oa.service;

import org.example.imooc_oa.Entity.Node;
import org.example.imooc_oa.mapper.RbacMapper;

import java.util.List;

public class RbacService {
    RbacMapper rbacMapper = new RbacMapper();
    public List<Node> selectNodeByUserId(long userId) {
        return rbacMapper.selectNodeByUserId(userId);
    }
}
