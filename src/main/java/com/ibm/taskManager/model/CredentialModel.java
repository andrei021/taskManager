package com.ibm.taskManager.model;

import java.util.Date;

public class CredentialModel {

    private Long id;
    private String username;
    private String password;
    private Date dateLastAccess;
    private Date dateCreateAccount;
    private EmployeeModel employee;

    public CredentialModel() {
    }

    public CredentialModel(
            Long id, String username,
            String password, Date dateLastAccess,
            Date dateCreateAccount, EmployeeModel employee) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.dateLastAccess = dateLastAccess;
        this.dateCreateAccount = dateCreateAccount;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDateLastAccess() {
        return dateLastAccess;
    }

    public void setDateLastAccess(Date dateLastAccess) {
        this.dateLastAccess = dateLastAccess;
    }

    public Date getDateCreateAccount() {
        return dateCreateAccount;
    }

    public void setDateCreateAccount(Date dateCreateAccount) {
        this.dateCreateAccount = dateCreateAccount;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }
}