package com.ibm.taskManager.model;

import lombok.Data;

@Data
public class EmployeeFormModel {
    private EmployeeModel employeeModel = new EmployeeModel();
    private CredentialModel credentialModel = new CredentialModel();
}
