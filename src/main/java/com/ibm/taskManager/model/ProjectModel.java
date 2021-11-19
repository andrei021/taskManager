package com.ibm.taskManager.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectModel {

    private Long id;
    private String idm;
    private String name;


    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date deadline;

    private List<TaskModel> taskList;

    public ProjectModel() {
    }

    public ProjectModel(
            Long id, String name,
            String status, Date deadline,
            List<TaskModel> taskList) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.deadline = deadline;
        this.taskList = taskList;
    }

    public void addTask(TaskModel taskModel) {
        if (this.taskList == null) {
            this.taskList = new ArrayList<>();
        }

        this.taskList.add(taskModel);
        taskModel.setProject(this);
    }

    public void removeTask(TaskModel taskModel) {
        this.taskList.remove(taskModel);
        taskModel.setProject(null);
    }

    public String getIdm() {
        return idm;
    }

    public void setIdm(String idm) {
        this.idm = idm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TaskModel> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskModel> taskList) {
        this.taskList = taskList;
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