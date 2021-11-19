package com.ibm.taskManager.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "tasks")
public class Task {

    @Id
    private Long taskId;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "deadline")
    @Temporal(TemporalType.DATE)
    private Date deadline;

    @ManyToOne
    private Project project;


    @OneToMany(
            mappedBy = "task",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<EmployeeTask> employeeTaskList;

    public Task() {
    }

    public Task(Long id, String name, String status, Date deadline, Project project) {
        this.taskId = id;
        this.name = name;
        this.status = status;
        this.deadline = deadline;
        this.project = project;
        this.employeeTaskList = new ArrayList<>();
    }

    public EmployeeTask addEmployeeToTaskAndTaskToEmployee(Employee employee) {
        EmployeeTask employeeTask = new EmployeeTask(employee, this);
        if (this.employeeTaskList == null) {
            this.employeeTaskList = new ArrayList<>();
        }

        this.employeeTaskList.add(employeeTask);
        List<EmployeeTask> employeeTaskListOfEmployee = employee.getEmployeeTaskList();
        if (employeeTaskListOfEmployee == null) {
            employeeTaskListOfEmployee = new ArrayList<>();
        }

        employeeTaskListOfEmployee.add(employeeTask);
        return employeeTask;
    }

    public void removeTaskFromEmployeeAndEmployeeFromTask(EmployeeTask employeeTask) {
        employeeTask.removeEmployeeAndTask(employeeTask.getEmployee(), this);
    }


    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public List<EmployeeTask> getEmployeeTaskList() {
        return employeeTaskList;
    }

    public void setEmployeeTaskList(List<EmployeeTask> employeeTaskList) {
        this.employeeTaskList = employeeTaskList;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}