package org.example.imooc_oa.mapper;

import org.example.imooc_oa.Entity.Employee;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperTest {

    @Test
    void selectEmployeeByEmployeeId() {
        EmployeeMapper employeeMapper = new EmployeeMapper();
        Employee employee = employeeMapper.selectEmployeeByEmployeeId(1);
        System.out.println(employee.getName());
    }

    @Test
    void testSelectEmployeeByEmployeeId() {
    }

    @Test
    void selectByParams() {
        EmployeeMapper employeeMapper = new EmployeeMapper();
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("level", 7);
        params.put("departmentId",2);
        List<Employee> employees = employeeMapper.selectByParams(params);
        employees.forEach(employee -> {
            System.out.println(employee.getName());
        });
    }
}