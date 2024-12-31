package org.example.imooc_oa.Entity;

import java.util.Date;

public class ProcessFlow {
    private long processId;
    private long formId;
    private long operatorId;
    private String action;
    private String result;
    private String reason;
    private Date createTime;
    private Date auditTime;
    private int orderNo;
    private String state;
    private int isLast;

    public ProcessFlow(long formId, long operatorId, String action, String result, String reason, Date createTime, Date auditTime, int orderNo, String state, int isLast) {
        this.formId = formId;
        this.operatorId = operatorId;
        this.action = action;
        this.result = result;
        this.reason = reason;
        this.createTime = createTime;
        this.auditTime = auditTime;
        this.orderNo = orderNo;
        this.state = state;
        this.isLast = isLast;
    }

    public ProcessFlow() {
    }

    @Override
    public String toString() {
        return "ProcessFlow{" +
                "processId=" + processId +
                ", formId=" + formId +
                ", operatorId=" + operatorId +
                ", action='" + action + '\'' +
                ", result='" + result + '\'' +
                ", reason='" + reason + '\'' +
                ", createTime=" + createTime +
                ", auditTime=" + auditTime +
                ", orderNo=" + orderNo +
                ", state='" + state + '\'' +
                ", isLast=" + isLast +
                '}';
    }

    public long getProcessId() {
        return processId;
    }

    public void setProcessId(long processId) {
        this.processId = processId;
    }

    public long getFormId() {
        return formId;
    }

    public void setFormId(long formId) {
        this.formId = formId;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getIsLast() {
        return isLast;
    }

    public void setIsLast(int isLast) {
        this.isLast = isLast;
    }
}
