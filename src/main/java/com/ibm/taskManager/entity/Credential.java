package com.ibm.taskManager.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "credentials")
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long credentialId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name="date_last_accessed")
    private Date dateLastAccess;

    @Column(name = "date_create_account")
    private Date dateCreateAccount;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Credential() {}

    public Credential(
            String username,
            String password, Date dateLastAccess,
            Date dateCreateAccount, Employee employee) {
        this.username = username;
        this.password = password;
        this.dateLastAccess = dateLastAccess;
        this.dateCreateAccount = dateCreateAccount;
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Employee getEmployeeEntity() {
        return employee;
    }

    public void setEmployeeEntity(Employee employee) {
        this.employee = employee;
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
}