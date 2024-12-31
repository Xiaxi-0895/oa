package org.example.imooc_oa.mapper;

import org.example.imooc_oa.Entity.LeaveForm;
import org.example.imooc_oa.Utils.MybatisUtils;

public class LeaveFormMapper {
    public void insertLeaveForm(LeaveForm leaveForm) {
        MybatisUtils.executeSql(sqlSession -> {
            sqlSession.insert("leave_form.insert", leaveForm);
            sqlSession.commit();
            System.out.println("提交成功");
            return null;
        });
    }
    public LeaveForm selectLeaveForm(long id) {
        return (LeaveForm) MybatisUtils.executeSql(sqlSession -> sqlSession.selectOne("leave_form.selectById", id));
    }
}
