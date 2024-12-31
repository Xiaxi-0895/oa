package org.example.imooc_oa.mapper;


import org.example.imooc_oa.Entity.LeaveForm;
import org.junit.jupiter.api.Test;

import java.sql.Date;


class LeaveFormMapperTest {

    @Test
    void insertLeaveForm() {
        LeaveFormMapper leaveFormMapper = new LeaveFormMapper();
        Date startDate =new Date(1730822400000L);
        Date endDate =new Date(1731513600000L);
        LeaveForm leaveForm = new LeaveForm(4,"2",startDate,endDate,"no why",startDate,"1");
        leaveFormMapper.insertLeaveForm(leaveForm);
    }
}