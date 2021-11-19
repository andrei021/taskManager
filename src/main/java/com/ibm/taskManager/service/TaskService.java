package com.ibm.taskManager.service;

import com.ibm.taskManager.entity.Employee;
import com.ibm.taskManager.entity.EmployeeTask;
import com.ibm.taskManager.entity.Project;
import com.ibm.taskManager.entity.Task;
import com.ibm.taskManager.model.EmployeeModel;
import com.ibm.taskManager.model.EmployeeTaskModel;
import com.ibm.taskManager.model.TaskModel;
import com.ibm.taskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private EmployeeTaskService employeeTaskService;

    @Autowired
    private EmployeeService employeeService;

    public Long getMaxId() {
        Long maxId = taskRepo.findMaxTaskId();
        return (maxId != null ? maxId : 0);
    }


    public void updateTask(Long id, TaskModel taskModel) {
        Task taskEntity = taskRepo.getById(id);
        taskEntity.setName(taskModel.getName());
        taskEntity.setStatus(taskModel.getStatus());
        taskEntity.setDeadline(taskModel.getDeadline());
        taskRepo.save(taskEntity);
    }

    public void addTask(TaskModel taskModel) {
        taskRepo.save(mapModelToEntity(taskModel));
    }

    public List<TaskModel> getAllTasksAssignedToEmployee(Long employeeId) {
        List<TaskModel> tasks = new ArrayList<>();

        for (EmployeeTaskModel employeeTaskModel : employeeTaskService.getAllEmployeeTask()) {
            if (employeeTaskModel.getEmployee().getId() == employeeId) {
                tasks.add(employeeTaskModel.getTask());
            }
        }

        return tasks;
    }

    public void addEmployee(Long taskId, EmployeeModel employeeModel) {
        Employee employeeEntity = employeeService.getEmployeeEntityById(employeeModel.getId());
        Task taskEntity = taskRepo.getById(taskId);

        EmployeeTask employeeTask = taskEntity.addEmployeeToTaskAndTaskToEmployee(employeeEntity);
        employeeTaskService.saveEntity(employeeTask);
    }

    public List<TaskModel> getAllTasks() {
        return taskRepo.findAll()
                .stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toList());
    }

    public void deleteTask(Long id) {
        Task taskEntity = taskRepo.getById(id);
        taskEntity.getEmployeeTaskList().clear();
        taskRepo.delete(taskEntity);
    }

    public TaskModel getTaskById(Long id) {
        Optional<Task> taskOptional = taskRepo.findById(id);
        TaskModel taskModel = null;

        if (taskOptional.isPresent()) {
            taskModel = mapEntityToModel(taskOptional.get());
        }

        return taskModel;
    }

    protected Task mapModelToEntity(TaskModel taskModel) {
        if (taskModel == null) {
            return null;
        }

        return new Task(
                taskModel.getId(),
                taskModel.getName(),
                taskModel.getStatus(),
                taskModel.getDeadline(),
                null
        );
    }

    protected TaskModel mapEntityToModel(Task taskEntity) {
        if (taskEntity == null) {
            return null;
        }

        return new TaskModel(
                taskEntity.getTaskId(),
                taskEntity.getName(),
                taskEntity.getStatus(),
                taskEntity.getDeadline(),
                null
        );
    }
}
