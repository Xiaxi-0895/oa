package org.example.imooc_oa.service;

import org.example.imooc_oa.Entity.Department;
import org.example.imooc_oa.mapper.DepartmentMapper;

public class DepartmentService {
    DepartmentMapper departmentMapper=new DepartmentMapper();
    public Department getDepartmentById(long id) {
        return departmentMapper.getDepartmentById(id);
    }
}
