package com.ibm.taskManager.entity;

import com.ibm.taskManager.entity.EmbeddedId.EmployeeTaskId;

import javax.persistence.*;

@Entity
public class EmployeeTask {

    @EmbeddedId
    private EmployeeTaskId employeeTaskId = new EmployeeTaskId();

    @ManyToOne
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
    private Task task;

    public EmployeeTask() {}

    public EmployeeTask(Employee employee, Task task) {
        this.employee = employee;
        this.task = task;
        this.employeeTaskId = new EmployeeTaskId();
        addEmployeeTaskIds(employee, task);
    }

    private void addEmployeeTaskIds(Employee employee, Task task) {
        employeeTaskId.setMyEmployeeId(employee.getEmployeeId());
        employeeTaskId.setTaskId(task.getTaskId());
    }

    public void removeEmployeeAndTask(Employee employee, Task task) {
        if (employee.getEmployeeTaskList() != null) {
            employee.getEmployeeTaskList().remove(this);
        }

        if (task.getEmployeeTaskList() != null) {
            task.getEmployeeTaskList().remove(this);
        }

        this.employee = null;
        this.task = null;
    }

    public EmployeeTaskId getEmployeeTaskId() {
        return employeeTaskId;
    }

    public void setEmployeeTaskId(EmployeeTaskId employeeTaskId) {
        this.employeeTaskId = employeeTaskId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}