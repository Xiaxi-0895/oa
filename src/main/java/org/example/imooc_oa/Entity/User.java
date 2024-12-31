package org.example.imooc_oa.Entity;

public class User {
    private long userId;
    private String username;
    private String password;
    private long employeeId;
    private int salt;

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }
}
