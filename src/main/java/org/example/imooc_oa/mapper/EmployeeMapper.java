package org.example.imooc_oa.mapper;

import org.example.imooc_oa.Entity.Employee;
import org.example.imooc_oa.Utils.MybatisUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeMapper {
    public Employee selectEmployeeByEmployeeId(long employeeId) {
        return (Employee) MybatisUtils.executeSql(sqlSession->sqlSession.selectOne("employee.selectByEmployeeId",employeeId));
    }
    public List<Employee> selectByParams(Map params) {
        List<Employee> list=(List<Employee>)MybatisUtils.executeSql(sqlSession -> {
            return sqlSession.selectList("employee.selectByParams",params);
        });
        return list;
    }
}
