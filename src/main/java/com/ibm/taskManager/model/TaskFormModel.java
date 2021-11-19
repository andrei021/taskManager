package com.ibm.taskManager.model;

import com.ibm.taskManager.entity.Project;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class TaskFormModel {
    private Long id;
    private String name;
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date deadline;

    private ProjectModel projectModel = new ProjectModel();

}
