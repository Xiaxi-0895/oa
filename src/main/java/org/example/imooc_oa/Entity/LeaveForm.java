package org.example.imooc_oa.Entity;

import java.util.Date;

public class LeaveForm {
    private long formId;
    private long employeeId;
    private String formType;
    private Date startTime;
    private Date endTime;
    private String reason;
    private Date createTime;
    private String state;

    public LeaveForm(long employeeId, String formType, Date startTime, Date endTime, String reason, Date createTime, String state) {
        this.employeeId = employeeId;
        this.formType = formType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
        this.createTime = createTime;
        this.state = state;
    }

    public LeaveForm() {
    }

    @Override
    public String toString() {
        return "LeaveForm{" +
                "formId=" + formId +
                ", employeeId=" + employeeId +
                ", formType='" + formType + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", reason='" + reason + '\'' +
                ", createTime=" + createTime +
                ", state='" + state + '\'' +
                '}';
    }

    public long getFormId() {
        return formId;
    }

    public void setFormId(long formId) {
        this.formId = formId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
