package org.example.imooc_oa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.imooc_oa.Entity.Department;
import org.example.imooc_oa.Entity.Employee;
import org.example.imooc_oa.Entity.LeaveForm;
import org.example.imooc_oa.Entity.ProcessFlow;
import org.example.imooc_oa.Utils.ResponseUtils;
import org.example.imooc_oa.service.DepartmentService;
import org.example.imooc_oa.service.EmployeeService;
import org.example.imooc_oa.service.LeaveFormService;

import java.io.IOException;
import java.util.*;

@WebServlet("/api/leave/*")
public class LeaveFormServlet extends HttpServlet {
    LeaveFormService leaveFormService = new LeaveFormService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        uri = uri.substring(uri.lastIndexOf("/") + 1);
        if (uri.equals("creat")) {
            this.create(req, resp);
        }else if (uri.equals("list")) {     //查询
            this.list(req, resp);
        }else if (uri.equals("audit")) {   //审核
            this.audit(req, resp);
        }



    }
    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("employeeId");
        String formType = req.getParameter("formType");
        String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");
        String reason = req.getParameter("reason");
        LeaveForm leaveForm = new LeaveForm();
        leaveForm.setEmployeeId(Long.parseLong(employeeId));
        leaveForm.setFormType(formType);
        leaveForm.setStartTime(new Date(Long.parseLong(startTime)));
        leaveForm.setEndTime(new Date(Long.parseLong(endTime)));
        leaveForm.setReason(reason);
        leaveForm.setCreateTime(new Date());
        ResponseUtils responseUtils;
        try{
            LeaveForm leaveForm1 = leaveFormService.createLeaveForm(leaveForm);
            System.out.println(leaveForm1);
            responseUtils = new ResponseUtils();
        }catch (Exception e){
            e.printStackTrace();
            responseUtils = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        resp.setContentType("application/json");
        resp.getWriter().println(responseUtils.toJson());

    }
    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("employeeId");
        EmployeeService employeeService = new EmployeeService();
        DepartmentService departmentService = new DepartmentService();
        List<ProcessFlow> list = leaveFormService.selectProcessFlowByOperatorId(Long.parseLong(employeeId));
        List records = new ArrayList();
        if(!list.isEmpty()){
            for (ProcessFlow flow : list) {
                if (flow.getOperatorId() == 1 && (!Objects.equals(flow.getAction(), "apply"))) {
                    ProcessFlow processFlow = leaveFormService.selectProcessFlowById(flow.getProcessId() - 1);
                    if ((Objects.equals(processFlow.getResult(), "approved")) && (!Objects.equals(flow.getResult(), "approved"))) {
                        getResult(employeeService, departmentService, records, flow);
                    }
                } else if (flow.getOperatorId() != 1) {
                    getResult(employeeService, departmentService, records, flow);
                }
            }
        }
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.put("list", records);
        resp.setContentType("application/json");
        resp.getWriter().println(responseUtils.toJson());
    }

    private void getResult(EmployeeService employeeService, DepartmentService departmentService, List records, ProcessFlow flow) {
        Map<String, Object> result;
        Employee employee;
        Department department;
        result = new LinkedHashMap<>();
        result.put("msg", leaveFormService.selectLeaveFormById(flow.getFormId()));
        employee = employeeService.getEmployeeById(leaveFormService.selectLeaveFormById(flow.getFormId()).getEmployeeId());
        result.put("name", employee.getName());
        department = departmentService.getDepartmentById(employee.getDepartmentId());
        result.put("departmentName", department.getDepartmentName());
        result.put("formId", flow.getFormId());
        result.put("operatorId", flow.getOperatorId());
        records.add(result);
    }

    private void audit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reason = req.getParameter("reason");
        String result = req.getParameter("result");
        String formId = req.getParameter("formId");
        String operatorId = req.getParameter("operatorId");
        leaveFormService.audit(Long.parseLong(formId), Long.parseLong(operatorId), result,reason);
        ResponseUtils responseUtils = new ResponseUtils();
        resp.setContentType("application/json");
        resp.getWriter().println(responseUtils.toJson());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
