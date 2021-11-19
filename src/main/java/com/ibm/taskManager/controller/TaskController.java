package com.ibm.taskManager.controller;

import com.ibm.taskManager.model.AddEmployeeToTaskFormModel;
import com.ibm.taskManager.model.EmployeeModel;
import com.ibm.taskManager.model.TaskModel;
import com.ibm.taskManager.service.EmployeeService;
import com.ibm.taskManager.service.EmployeeTaskService;
import com.ibm.taskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private EmployeeTaskService employeeTaskService;

    @GetMapping(value = "/task/{id}")
    public String showProject(@PathVariable String id, Model model) {
        TaskModel taskModel = taskService.getTaskById(Long.parseLong(id));
        List<EmployeeModel> employeesAssignedToTask = employeeService.getAllEmployeesAssignedToTask(Long.parseLong(id));
        taskModel.setIdm(id);

        AddEmployeeToTaskFormModel addEmployeeToTaskFormModel = new AddEmployeeToTaskFormModel();
        addEmployeeToTaskFormModel.setId(taskModel.getId());
        addEmployeeToTaskFormModel.setIdm(id);
        addEmployeeToTaskFormModel.setEmployees(
                employeeService.getAllEmployeesNotAssignedToTask(taskModel.getId())
        );

        model.addAttribute("task", taskModel);
        model.addAttribute("employees", employeesAssignedToTask);
        model.addAttribute("employeeToTaskFormModel", addEmployeeToTaskFormModel);

        return "task";
    }

    @PostMapping(value = "/task/{id}/addEmployee")
    public String addEmployee(@PathVariable String id, AddEmployeeToTaskFormModel addEmployeeToTaskFormModel) {
        taskService.addEmployee(
                Long.parseLong(id),
                employeeService.getEmployeeByUsername(addEmployeeToTaskFormModel.getNewUsername())
        );
        return "redirect:/task/" + id;
    }

    @PutMapping(value = "/task/{id}/updateTask")
    public String updateTask(@PathVariable("id") String id, TaskModel taskModel) {
        taskService.updateTask(Long.parseLong(id), taskModel);
        return "redirect:/task/"  + id;
    }

    @DeleteMapping(value = "/task/{id}/deleteTask")
    public String deleteTask(@PathVariable String id, TaskModel taskModel) {
        taskService.deleteTask(taskModel.getId());
        return "redirect:/";
    }

    @GetMapping(value = "/task/{taskId}/{userId}/removeUser")
    public String removeUser(@PathVariable String taskId, @PathVariable String userId) {
        employeeService.removeTask(
                Long.parseLong(userId),
                Long.parseLong(taskId)
        );
        return "redirect:/task/" + taskId;
    }
}