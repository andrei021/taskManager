package com.ibm.taskManager.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long projectId;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "deadline")
    @Temporal(TemporalType.DATE)
    private Date deadline;

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Task> taskList;

    public Project() {
    }

    public Project(String name, String status, Date deadline) {
        this.name = name;
        this.status = status;
        this.deadline = deadline;
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        if (this.taskList == null) {
            this.taskList = new ArrayList<>();
        }

        this.taskList.add(task);
        task.setProject(this);
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void removeTask(Task task) {
        this.taskList.remove(task);
        task.setProject(null);
    }

    public List<Task> getTasks() {
        return taskList;
    }

    public void setTasks(List<Task> taskEntities) {
        this.taskList = taskEntities;
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