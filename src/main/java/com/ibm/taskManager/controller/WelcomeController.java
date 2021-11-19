package com.ibm.taskManager.controller;

import com.ibm.taskManager.model.CredentialModel;
import com.ibm.taskManager.model.EmployeeFormModel;
import com.ibm.taskManager.model.EmployeeModel;
import com.ibm.taskManager.model.ProjectModel;
import com.ibm.taskManager.service.EmployeeService;
import com.ibm.taskManager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WelcomeController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public String welcome(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("employeeFormModel", new EmployeeFormModel());
        model.addAttribute("projectModel", new ProjectModel());
        return "welcome";
    }

    @PostMapping(value = "/addEmployee")
    public String addEmployee(EmployeeFormModel employeeFormModel) {
        EmployeeModel employeeModel = employeeFormModel.getEmployeeModel();
        CredentialModel credentialModel = employeeFormModel.getCredentialModel();
        employeeModel.setCredential(credentialModel);

        employeeService.addEmployee(employeeModel);
        String recentlySavedEmployeeId = employeeService
                .getEmployeeById(employeeService.getMaxId())
                .getId()
                .toString();
        return "redirect:/user/" + recentlySavedEmployeeId;
    }

    @PostMapping(value = "/addProject")
    public String addProject(ProjectModel projectModel) {
        projectService.addProject(projectModel);
        return "redirect:/";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(
                Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }
}