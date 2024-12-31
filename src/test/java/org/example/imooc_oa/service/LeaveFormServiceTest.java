package org.example.imooc_oa.service;

import org.example.imooc_oa.Entity.LeaveForm;
import org.example.imooc_oa.Entity.ProcessFlow;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class LeaveFormServiceTest {

    @Test
    void createLeaveForm() throws ParseException {
        LeaveFormService leaveFormService = new LeaveFormService();
        LeaveForm leaveForm = new LeaveForm();
        leaveForm.setEmployeeId(10);
        leaveForm.setFormType("2");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--hh:mm:ss");
        Date start = sdf.parse("2024-11-14--00:00:00");
        Date end = sdf.parse("2024-11-18--00:00:00");
        leaveForm.setStartTime(start);
        leaveForm.setEndTime(end);
        leaveForm.setReason("身体难受");
        leaveForm.setCreateTime(new Date());
        LeaveForm form = leaveFormService.createLeaveForm(leaveForm);
        System.out.println(form.getFormId());
    }

    @Test
    void selectProcessFlow() {
        LeaveFormService leaveFormService = new LeaveFormService();
        List<ProcessFlow> list = leaveFormService.selectProcessFlowByOperatorId(6);
        System.out.println(list);
    }

    @Test
    void selectProcessFlowById() {
        LeaveFormService leaveFormService = new LeaveFormService();

    }
}