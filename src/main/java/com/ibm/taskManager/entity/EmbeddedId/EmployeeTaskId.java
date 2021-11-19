package com.ibm.taskManager.entity.EmbeddedId;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MapsId;

import java.io.Serializable;

@Embeddable
public class EmployeeTaskId implements Serializable {

    @MapsId("employeeId")
    @Column(name = "employee_id")
    private Long myEmployeeId;

    @MapsId("taskId")
    @Column(name = "task_id")
    private Long taskId;

    public Long getMyEmployeeId() {
        return myEmployeeId;
    }

    public void setMyEmployeeId(Long employeeId) {
        this.myEmployeeId = employeeId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}