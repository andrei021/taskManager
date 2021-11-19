package com.ibm.taskManager.model;

import lombok.Data;

import java.util.List;

@Data
public class AddEmployeeToTaskFormModel {
    private Long id;
    private String idm;
    private List<EmployeeModel> employees;
    private String newUsername;
}
