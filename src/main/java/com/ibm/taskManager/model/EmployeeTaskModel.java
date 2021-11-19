package com.ibm.taskManager.model;

public class EmployeeTaskModel {

    private EmployeeModel employee;
    private TaskModel task;

    public EmployeeTaskModel() {
    }

    public EmployeeTaskModel(EmployeeModel employee, TaskModel task) {
        this.employee = employee;
        this.task = task;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }

    public TaskModel getTask() {
        return task;
    }

    public void setTask(TaskModel task) {
        this.task = task;
    }
}
