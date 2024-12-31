package org.example.imooc_oa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.imooc_oa.Entity.Department;
import org.example.imooc_oa.Entity.Employee;
import org.example.imooc_oa.Entity.Node;
import org.example.imooc_oa.Utils.ResponseUtils;
import org.example.imooc_oa.service.DepartmentService;
import org.example.imooc_oa.service.EmployeeService;
import org.example.imooc_oa.service.RbacService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/api/user_info")
public class UserInfoServlet extends HttpServlet {
    RbacService rbacService = new RbacService();
    EmployeeService employeeService = new EmployeeService();
    DepartmentService departmentService = new DepartmentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(req.getParameter("userId"));
        int employeeId = Integer.parseInt(req.getParameter("employeeId"));
        List<Node> list = rbacService.selectNodeByUserId(id);
        Employee employee = employeeService.getEmployeeById(employeeId);
        Department department= departmentService.getDepartmentById(employee.getDepartmentId());
        List<Map> treeMaps = new ArrayList<>();
        Map map=null;
        for (Node node : list) {
            if(node.getNodeType()==1){
                map = new LinkedHashMap();
                map.put("node",node);
                map.put("children",new ArrayList<Node>());
                treeMaps.add(map);
            }else if(node.getNodeType()==2){
                List list1 = (List) map.get("children");
                list1.add(node);
            }
        }
        resp.getWriter().println(new ResponseUtils().put("nodelist",treeMaps).put("employee",employee).put("department",department).toJson());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
