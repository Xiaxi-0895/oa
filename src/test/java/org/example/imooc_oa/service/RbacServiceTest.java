package org.example.imooc_oa.service;

import org.example.imooc_oa.Entity.Node;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RbacServiceTest {

    @Test
    void selectNodeByUserId() {
        RbacService rbacService = new RbacService();
        List<Node> list = rbacService.selectNodeByUserId(2);
        list.forEach(s-> System.out.println(s.getNodeName()));
    }
}