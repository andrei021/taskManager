package com.ibm.taskManager.controller;

import com.ibm.taskManager.model.EmployeeModel;
import com.ibm.taskManager.model.EmployeeTaskModel;
import com.ibm.taskManager.model.TaskModel;
import com.ibm.taskManager.service.EmployeeService;
import com.ibm.taskManager.service.EmployeeTaskService;
import com.ibm.taskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private EmployeeTaskService employeeTaskService;

    @GetMapping(value = "/user/{id}")
    public String showUser(@PathVariable String id, Model model) {
        List<TaskModel> taskList = taskService.getAllTasksAssignedToEmployee(Long.parseLong(id));
        EmployeeModel employee = employeeService.getEmployeeById(Long.parseLong(id));
        employee.setIdm(id);

        model.addAttribute("tasks", taskList);
        model.addAttribute("employee", employee);

        return "employee";
    }

    @PutMapping(value = "/user/{id}/updateUser")
    public String updateUser(@PathVariable String id, EmployeeModel employeeModel) {
        employeeService.updateEmployee(employeeModel);
        return "redirect:/user/" + id;
    }

    @GetMapping(value = "/user/{userId}/{taskId}/removeTask")
    public String removeTask(@PathVariable String userId, @PathVariable String taskId) {
        employeeService.removeTask(
                Long.parseLong(userId),
                Long.parseLong(taskId)
        );
        return "redirect:/user/" + userId;
    }

    @DeleteMapping(value = "/user/{id}/deleteUser")
    public String deleteUser(@PathVariable String id) {
        employeeService.deleteEmployee(Long.parseLong(id));
        return "redirect:/";
    }
}
