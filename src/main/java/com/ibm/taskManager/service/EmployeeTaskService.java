package com.ibm.taskManager.service;

import com.ibm.taskManager.entity.Employee;
import com.ibm.taskManager.entity.EmployeeTask;
import com.ibm.taskManager.model.EmployeeModel;
import com.ibm.taskManager.model.EmployeeTaskModel;
import com.ibm.taskManager.repository.EmployeeTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeTaskService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeTaskRepository employeeTaskRepo;

    public void saveEntity(EmployeeTask employeeTask) {
        employeeTaskRepo.save(employeeTask);
    }

    public List<EmployeeTaskModel> getAllEmployeeTask() {
        return employeeTaskRepo.findAll()
                .stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toList());
    }

    public EmployeeTask mapModelToEntity(EmployeeTaskModel employeeTaskModel) {
        return new EmployeeTask(
               employeeService.mapModelToEntity(employeeTaskModel.getEmployee()),
               taskService.mapModelToEntity(employeeTaskModel.getTask())
        );
    }

    public List<EmployeeTask> getEmployeeTask(Long userId, Long taskId) {
        return employeeTaskRepo.findEmployeeTask(userId, taskId);
    }

    public EmployeeTaskModel mapEntityToModel(EmployeeTask employeeTask) {
        return new EmployeeTaskModel(
                employeeService.mapEntityToModel(employeeTask.getEmployee()),
                taskService.mapEntityToModel(employeeTask.getTask())
        );
    }

    public void deleteEmployeesTask(Employee employeeEntity) {
        for (EmployeeTask employeeTask : employeeEntity.getEmployeeTaskList()) {
            employeeTaskRepo.delete(employeeTask);
        }
    }

    public void removeEmployeeTask(EmployeeTask employeeTaskEntity) {
        employeeTaskRepo.delete(employeeTaskEntity);
    }
}
