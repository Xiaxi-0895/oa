package org.example.imooc_oa.mapper;

import org.example.imooc_oa.Entity.Department;
import org.example.imooc_oa.Utils.MybatisUtils;

public class DepartmentMapper {
    public Department getDepartmentById(long id) {
        return (Department) MybatisUtils.executeSql(sqlSession -> sqlSession.selectOne("department.selectById", id));
    }
}
