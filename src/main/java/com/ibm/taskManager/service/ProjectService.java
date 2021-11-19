package com.ibm.taskManager.service;

import com.ibm.taskManager.entity.Project;
import com.ibm.taskManager.entity.Task;
import com.ibm.taskManager.model.ProjectModel;
import com.ibm.taskManager.model.TaskModel;
import com.ibm.taskManager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private TaskService taskService;

    public void addProject(ProjectModel projectModel) {
        projectModel.setStatus("in progress");
        projectModel.setTaskList(new ArrayList<>());

        projectRepo.save(mapModelToEntity(projectModel));
    }

    public void deleteProject(Long id) {
        Project projectEntity = projectRepo.getById(id);
        projectEntity.getTaskList().clear();
        projectRepo.delete(projectEntity);
    }

    public void updateProject(Long id, ProjectModel projectModel) {
        Project projectEntity = projectRepo.getById(id);
        projectEntity.setName(projectModel.getName());
        projectEntity.setDeadline(projectModel.getDeadline());
        projectEntity.setStatus(projectModel.getStatus());
        projectRepo.save(projectEntity);
    }

    public void addTaskToProject(TaskModel taskModel, Long projectId) {
        taskModel.setId(taskService.getMaxId() + 1);
        taskModel.setStatus("in progress");

        Project projectEntity = projectRepo.getById(projectId);
        projectEntity.addTask(taskService.mapModelToEntity(taskModel));
        projectRepo.save(projectEntity);
    }

    public List<ProjectModel> getAllProjects() {
        return projectRepo.findAll()
                .stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toList());
    }

    public ProjectModel getProjectById(Long id) {
        Optional<Project> projectOptional = projectRepo.findById(id);
        ProjectModel projectModel = null;

        if (projectOptional.isPresent()) {
            projectModel = mapEntityToModel(projectOptional.get());
        }

        return projectModel;
    }

    protected Project mapModelToEntity(ProjectModel projectModel) {
        if (projectModel == null) {
            return null;
        }

        Project projectEntity = new Project(
                projectModel.getName(),
                projectModel.getStatus(),
                projectModel.getDeadline()
        );

        for (TaskModel taskModel : projectModel.getTaskList()) {
            Task taskEntity = taskService.mapModelToEntity(taskModel);

            if (taskEntity != null) {
                projectEntity.addTask(taskEntity);
            }
        }

        return projectEntity;
    }

    protected ProjectModel mapEntityToModel(Project projectEntity) {
        if (projectEntity == null) {
            return null;
        }

        ProjectModel projectModel = new ProjectModel(
                projectEntity.getProjectId(),
                projectEntity.getName(),
                projectEntity.getStatus(),
                projectEntity.getDeadline(),
                null
        );

        for (Task taskEntity : projectEntity.getTaskList()) {
            TaskModel taskModel = taskService.mapEntityToModel(taskEntity);

            if (taskModel != null) {
                projectModel.addTask(taskModel);
            }
        }

        return projectModel;
    }
}
