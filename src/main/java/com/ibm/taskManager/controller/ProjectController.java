package com.ibm.taskManager.controller;

import com.ibm.taskManager.model.ProjectModel;
import com.ibm.taskManager.model.TaskModel;
import com.ibm.taskManager.service.ProjectService;
import com.ibm.taskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ProjectController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/project/{id}")
    public String showProject(@PathVariable String id, Model model) {
        ProjectModel projectModel = projectService.getProjectById(Long.parseLong(id));
        projectModel.setIdm(id);
        TaskModel taskModel = new TaskModel();
        taskModel.setProject(projectModel);
        taskModel.setIdm(projectModel.getId().toString());

        model.addAttribute("tasks", projectModel.getTaskList());
        model.addAttribute("project", projectModel);
        model.addAttribute("taskModel", taskModel);
        return "project";
    }

    @PostMapping(value = "/project/{id}/addTask")
    public String addTask(@PathVariable("id") String id, TaskModel taskModel) {
        projectService.addTaskToProject(taskModel, Long.parseLong(id));
        return "redirect:/project/" + id;
    }

    @PutMapping(value = "/project/{id}/updateProject")
    public String updateProject(@PathVariable("id") String id, ProjectModel projectModel) {
        projectService.updateProject(Long.parseLong(id), projectModel);
        return "redirect:/project/" + id;
    }

    @DeleteMapping(value = "/project/{id}/deleteProject")
    public String deleteProject(@PathVariable("id") String id) {
        projectService.deleteProject(Long.parseLong(id));
        return "redirect:/";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(
                Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10)
        );
    }
}