package org.example.imooc_oa.mapper;

import org.example.imooc_oa.Entity.ProcessFlow;
import org.example.imooc_oa.Utils.MybatisUtils;

import java.util.List;
import java.util.Map;

public class ProcessFlowMapper {
    public void insertProcessFlow(ProcessFlow processFlow) {
        MybatisUtils.executeSql(sqlSession -> {
            sqlSession.insert("process_flow.insert", processFlow);
            sqlSession.commit();
            return null;
        });
    }
    public List<ProcessFlow> selectProcessFlowByOperatorId(long id) {
        return (List<ProcessFlow>) MybatisUtils.executeSql(sqlSession -> sqlSession.<ProcessFlow>selectList("process_flow.selectByOperatorId", id));
    }
    public ProcessFlow selectProcessFlowById(long id) {
        return (ProcessFlow) MybatisUtils.executeSql(sqlSession -> sqlSession.selectOne("process_flow.selectById", id));
    }
    public long selectApplicationByFormId(long formId) {
        return (long) MybatisUtils.executeSql(sqlSession -> sqlSession.selectOne("process_flow.selectApplicantByFormId", formId));
    }
    public long selectLeaderIdByFormId(long formId) {
        return (long) MybatisUtils.executeSql(sqlSession -> sqlSession.selectOne("process_flow.selectApplicantByFormId", formId));
    }
}
