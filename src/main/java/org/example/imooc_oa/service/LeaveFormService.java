package org.example.imooc_oa.service;

import org.example.imooc_oa.Entity.Employee;
import org.example.imooc_oa.Entity.LeaveForm;
import org.example.imooc_oa.Entity.Notice;
import org.example.imooc_oa.Entity.ProcessFlow;
import org.example.imooc_oa.Utils.MybatisUtils;
import org.example.imooc_oa.mapper.EmployeeMapper;
import org.example.imooc_oa.mapper.LeaveFormMapper;
import org.example.imooc_oa.mapper.NoticeMapper;
import org.example.imooc_oa.mapper.ProcessFlowMapper;
import java.text.SimpleDateFormat;
import java.util.*;

public class LeaveFormService {                                         //从网页获取的数据存入到leave_form表中,将详细的流程通过leaveFormService存入process_flow表中(审批的发起，需要审批的人员)
    LeaveFormMapper leaveFormMapper=new LeaveFormMapper();
    EmployeeService employeeService=new EmployeeService();
    ProcessFlowMapper processFlowMapper=new ProcessFlowMapper();
    NoticeMapper noticeMapper=new NoticeMapper();
    public LeaveForm createLeaveForm(LeaveForm leaveForm){
        Employee employee = employeeService.getEmployeeById(leaveForm.getEmployeeId());
        if(employee.getLevel()==8){                                   //总经理发起请假自动通过
            leaveForm.setState("approved");
        }else {                                                       //其他人则处于审批状态
            leaveForm.setState("processing");
        }
        leaveFormMapper.insertLeaveForm(leaveForm);             //向数据库中存入请假数据
        ProcessFlow processFlow1 = new ProcessFlow();           //创建审批请求
        processFlow1.setFormId(leaveForm.getFormId());
        processFlow1.setOperatorId(employee.getEmployeeId());
        processFlow1.setAction("apply");
        processFlow1.setCreateTime(new Date());
        processFlow1.setOrderNo(1);
        processFlow1.setState("complete");
        processFlow1.setIsLast(0);
        processFlowMapper.insertProcessFlow(processFlow1);
        Employee leader = employeeService.selectLeader(employee.getEmployeeId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH时");
        if(employee.getLevel()<7){                                   //如果是基层人员则创建审批流程向上级申请
            ProcessFlow processFlow2 = new ProcessFlow();
            processFlow2.setFormId(leaveForm.getFormId());
            processFlow2.setOperatorId(leader.getEmployeeId());
            processFlow2.setAction("audit");
            processFlow2.setCreateTime(new Date());
            processFlow2.setOrderNo(2);
            processFlow2.setState("process");
            float day = ((float) (leaveForm.getEndTime().getTime() - leaveForm.getStartTime().getTime()) /(1000*60*60*24));
            if(day>=3){                                                     //如果天数大于三天则需要额外向总经理申请审批
                processFlow2.setIsLast(0);
                processFlowMapper.insertProcessFlow(processFlow2);
                Employee manager = employeeService.selectLeader(leader.getEmployeeId());
                ProcessFlow processFlow3 = new ProcessFlow();
                processFlow3.setFormId(leaveForm.getFormId());
                processFlow3.setOperatorId(manager.getEmployeeId());
                processFlow3.setAction("audit");
                processFlow3.setCreateTime(new Date());
                processFlow3.setOrderNo(3);
                processFlow3.setState("ready");//等待部门经理审批
                processFlow3.setIsLast(1);
                processFlowMapper.insertProcessFlow(processFlow3);
            }else {                                                         //如果小于三天则只向部门经理发送即可
                processFlow2.setIsLast(1);
                processFlowMapper.insertProcessFlow(processFlow2);
            }
            String notice1 = String.format("您的请假申请[%s-%s]已提交,请等待上级审批.",sdf.format(leaveForm.getStartTime()),sdf.format(leaveForm.getEndTime()));
            noticeMapper.insertNotice(new Notice(employee.getEmployeeId(),notice1,new Date()));
            String notice2 = String.format("%s-%s提起的请假申请[%s-%s],请尽快审批",employee.getTitle(),employee.getName(),sdf.format(leaveForm.getStartTime()),sdf.format(leaveForm.getEndTime()));
            noticeMapper.insertNotice(new Notice(leader.getEmployeeId(),notice2,new Date()));
        }
        else if(employee.getLevel()==7){                           //如果是部门经理请假则需向总经理发出请假审批
            ProcessFlow processFlow2 = new ProcessFlow();
            processFlow2.setFormId(leaveForm.getFormId());
            processFlow2.setOperatorId(leader.getEmployeeId());
            processFlow2.setAction("audit");
            processFlow2.setCreateTime(new Date());
            processFlow2.setOrderNo(2);
            processFlow2.setState("process");
            processFlow2.setIsLast(1);
            processFlowMapper.insertProcessFlow(processFlow2);
            String notice1 = String.format("您的请假申请[%s-%s]已提交,请等待上级审批.",sdf.format(leaveForm.getStartTime()),sdf.format(leaveForm.getEndTime()));
            noticeMapper.insertNotice(new Notice(employee.getEmployeeId(),notice1,new Date()));
            String notice2 = String.format("%s-%s提起的请假申请[%s-%s],请尽快审批",employee.getTitle(),employee.getName(),sdf.format(leaveForm.getStartTime()),sdf.format(leaveForm.getEndTime()));
            noticeMapper.insertNotice(new Notice(leader.getEmployeeId(),notice2,new Date()));
        }
        else if(employee.getLevel()==8){                           //如果是总经理则直接通过审批
            ProcessFlow processFlow2 = new ProcessFlow();
            processFlow2.setFormId(leaveForm.getFormId());
            processFlow2.setOperatorId(leaveForm.getEmployeeId());
            processFlow2.setAction("audit");
            processFlow2.setResult("approved");
            processFlow2.setReason("自动通过");
            processFlow2.setCreateTime(new Date());
            processFlow2.setAuditTime(new Date());
            processFlow2.setOrderNo(2);
            processFlow2.setState("complete");
            processFlow2.setIsLast(1);
            processFlowMapper.insertProcessFlow(processFlow2);
            String notice = String.format("您的请假申请[%s-%s]系统已自动审批通过过",sdf.format(leaveForm.getStartTime()),sdf.format(leaveForm.getEndTime()));
            noticeMapper.insertNotice(new Notice(employee.getEmployeeId(),notice,new Date()));
        }
        return leaveForm;
    }
    public List<ProcessFlow> selectProcessFlowByOperatorId(long id){
        return processFlowMapper.selectProcessFlowByOperatorId(id);
    }
    public ProcessFlow selectProcessFlowById(long id){
        return processFlowMapper.selectProcessFlowById(id);
    }
    public LeaveForm selectLeaveFormById(long id){
        return leaveFormMapper.selectLeaveForm(id);
    }
    public void audit(long formId,long operatorId,String result,String reason){
        MybatisUtils.executeSql(sqlSession -> {
            Map<String, Object> paramMap = new LinkedHashMap<>();
            paramMap.put("formId", formId);
            paramMap.put("operatorId", operatorId);
            paramMap.put("result", result);
            paramMap.put("reason", reason);
            paramMap.put("auditTime", new Date());
            sqlSession.update("process_flow.updateProcessFlow",paramMap);
            sqlSession.commit();
            EmployeeMapper employeeMapper = new EmployeeMapper();
            LeaveFormMapper leaveFormMapper = new LeaveFormMapper();
            LeaveForm leaveForm = leaveFormMapper.selectLeaveForm(formId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH时");
            Employee employee;
            if(processFlowMapper.selectProcessFlowById(formId).getIsLast()==0&& Objects.equals(result, "approved")){
                long applicationId = processFlowMapper.selectApplicationByFormId(formId);
                employee = employeeMapper.selectEmployeeByEmployeeId(applicationId);
                String notice1 = String.format("[%s-%s]提起的请假申请[%s-%s]您已批准,审批意见:%s,申请转至上级领导继续审批",employee.getTitle(),employee.getName(),sdf.format(leaveForm.getStartTime()),sdf.format(leaveForm.getEndTime()),reason);
                noticeMapper.insertNotice(new Notice(employee.getEmployeeId(),notice1,new Date()));
                Employee leader = employeeMapper.selectEmployeeByEmployeeId(processFlowMapper.selectLeaderIdByFormId(formId));
                String notice2 = String.format("%s-%s提起的请假申请[%s-%s],请尽快审批",employee.getTitle(),employee.getName(),sdf.format(leaveForm.getStartTime()),sdf.format(leaveForm.getEndTime()));
                noticeMapper.insertNotice(new Notice(leader.getEmployeeId(),notice2,new Date()));
            } else if (processFlowMapper.selectProcessFlowById(formId).getIsLast()==1&& Objects.equals(result, "approved")) {
                employee = employeeMapper.selectEmployeeByEmployeeId(operatorId);
                String notice1 = String.format("您的请假申请[%s-%s]%s%s已批准,审批意见:%s,审批流程已结束",sdf.format(leaveForm.getStartTime()),sdf.format(leaveForm.getEndTime()),employee.getTitle(),employee.getName(),reason);
            } else if (Objects.equals(result,"refused")) {
                employee = employeeMapper.selectEmployeeByEmployeeId(operatorId);
                String notice1 = String.format("您的请假申请[%s-%s]被%s%s驳回,审批意见:%s,审批流程已结束",sdf.format(leaveForm.getStartTime()),sdf.format(leaveForm.getEndTime()),employee.getTitle(),employee.getName(),reason);
            }
            return null;
        });
    }

}
