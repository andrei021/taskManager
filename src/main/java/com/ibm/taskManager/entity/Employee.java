package com.ibm.taskManager.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "employees")
public class Employee {

    @Id
    private Long employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "role")
    private String role;

    @OneToOne(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Credential credentials;

    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<EmployeeTask> employeeTaskList;

    public Employee() {}

    public Employee(
            Long id, String firstName,
            String lastName, String phone,
            String email, String address,
            String role, Credential credentials) {
        this.employeeId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.credentials = credentials;
        this.employeeTaskList = new ArrayList<>();
    }

    public void addTaskToEmployeeAndEmployeeToTask(Task task) {
        EmployeeTask employeeTask = new EmployeeTask(this, task);
        if (this.employeeTaskList == null) {
            this.employeeTaskList = new ArrayList<>();
        }

        this.employeeTaskList.add(employeeTask);
        List<EmployeeTask> employeeTaskListOfTask = task.getEmployeeTaskList();
        if (employeeTaskListOfTask == null) {
            employeeTaskListOfTask = new ArrayList<>();
        }

        employeeTaskListOfTask.add(employeeTask);
    }

    public void removeTaskFromEmployeeAndEmployeeFromTask(EmployeeTask employeeTask) {
        employeeTask.removeEmployeeAndTask(this, employeeTask.getTask());
    }

    public List<EmployeeTask> getEmployeeTaskList() {
        return employeeTaskList;
    }

    public void setEmployeeTaskList(List<EmployeeTask> employeeTaskList) {
        this.employeeTaskList = employeeTaskList;
    }

    public void setCredentials(Credential credentials) {
        if (credentials == null) {
            if (this.credentials != null) {
                this.credentials.setEmployee(null);
            }
        } else {
            credentials.setEmployee(this);
        }

        this.credentials = credentials;
    }

    public void removeCredentials(Credential credentials) {
        credentials.setEmployee(null);
        this.credentials = null;
    }

    public Credential getCredentials() {
        return credentials;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}