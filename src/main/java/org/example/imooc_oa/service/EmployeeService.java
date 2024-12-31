package org.example.imooc_oa.service;

import org.example.imooc_oa.Entity.Employee;
import org.example.imooc_oa.mapper.EmployeeMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService {
    EmployeeMapper employeeMapper=new EmployeeMapper();
    public Employee getEmployeeById(long id) {
        return employeeMapper.selectEmployeeByEmployeeId(id);
    }

    public Employee selectLeader(long employeeId) {
        Employee employee=employeeMapper.selectEmployeeByEmployeeId(employeeId);
        Map<String,Object> map=new LinkedHashMap<>();
        if(employee.getLevel()>=7){
            map.put("level",8);
        }else{
            map.put("level",7);
            map.put("departmentId",employee.getDepartmentId());
        }
        List<Employee> list = employeeMapper.selectByParams(map);
        return list.getFirst();
    }
}
