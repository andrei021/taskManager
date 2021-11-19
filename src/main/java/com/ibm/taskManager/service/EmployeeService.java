package com.ibm.taskManager.service;

import com.ibm.taskManager.entity.Employee;
import com.ibm.taskManager.entity.EmployeeTask;
import com.ibm.taskManager.model.EmployeeModel;
import com.ibm.taskManager.model.EmployeeTaskModel;
import com.ibm.taskManager.repository.EmployeeRepository;
import com.ibm.taskManager.util.DateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private EmployeeTaskService employeeTaskService;

    @Autowired
    EmployeeRepository employeeRepo;

    public Long getMaxId() {
        Long maxId = employeeRepo.findMaxEmployeeId();
        return (maxId != null ? maxId : 0);
    }

    public Employee getEmployeeEntityById(Long id) {
        return employeeRepo.getById(id);
    }

    public void removeTask(Long userId, Long taskId) {
        Employee employeeEntity = employeeRepo.getById(userId);
        EmployeeTask employeeTaskEntity = employeeTaskService.getEmployeeTask(userId, taskId).get(0);
        employeeEntity.removeTaskFromEmployeeAndEmployeeFromTask(employeeTaskEntity);
        employeeTaskService.removeEmployeeTask(employeeTaskEntity);
    }

    public void updateEmployee(EmployeeModel employeeModel) {
        Employee employee = employeeRepo.getById(employeeModel.getId());
        employee.setFirstName(employeeModel.getFirstName());
        employee.setLastName(employeeModel.getLastName());
        employee.setEmail(employeeModel.getEmail());
        employee.setAddress(employeeModel.getAddress());
        employee.setPhone(employeeModel.getPhone());
        employee.setRole(employeeModel.getRole());
        employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employeeEntity = employeeRepo.getById(id);
        employeeEntity.getEmployeeTaskList().clear();
        employeeRepo.delete(employeeEntity);
    }

    public void addEmployee(EmployeeModel employeeModel) {
        employeeModel.setId(getMaxId() + 1);
        employeeModel.getCredential().setDateCreateAccount(DateProvider.getCurrentDate());
        employeeRepo.save(mapModelToEntity(employeeModel));
    }

    public EmployeeModel getEmployeeByUsername(String username) {
        for (EmployeeModel employee : getAllEmployees()) {
            if (employee.getCredential().getUsername().equals(username)) {
                return employee;
            }
        }

        return null;
    }

    public List<EmployeeModel> getAllEmployeesAssignedToTask(Long taskId) {
        List<EmployeeModel> employees = new ArrayList<>();

        for (EmployeeTaskModel employeeTaskModel : employeeTaskService.getAllEmployeeTask()) {
            if (employeeTaskModel.getTask().getId() == taskId) {
                employees.add(employeeTaskModel.getEmployee());
            }
        }

        return employees;
    }

    public List<EmployeeModel> getAllEmployeesNotAssignedToTask(Long taskId) {
        List<EmployeeModel> employees = new ArrayList<>();

        for (EmployeeTaskModel employeeTaskModel : employeeTaskService.getAllEmployeeTask()) {
            if (employeeTaskModel.getTask().getId() != taskId) {
                if (!containsEmployee(employeeTaskModel.getEmployee().getId(), employees)) {
                    employees.add(employeeTaskModel.getEmployee());
                }
            }
        }

        return employees;
    }

    private boolean containsEmployee(Long id, List<EmployeeModel> employees) {
        for (EmployeeModel employeeModel : employees) {
            if (employeeModel.getId() == id) {
                return true;
            }
        }

        return false;
    }

    public List<EmployeeModel> getAllEmployees() {
        return employeeRepo.findAll()
                .stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toList());
    }

    public EmployeeModel getEmployeeById(long id) {
        Optional<Employee> employeeOptional = employeeRepo.findById(id);
        EmployeeModel employeeModel = null;

        if (employeeOptional.isPresent()) {
            employeeModel = mapEntityToModel(employeeOptional.get());
        }

        return employeeModel;
    }

    protected Employee mapModelToEntity(EmployeeModel employeeModel) {
        Employee employeeEntity = new Employee(
                employeeModel.getId(),
                employeeModel.getFirstName(),
                employeeModel.getLastName(),
                employeeModel.getPhone(),
                employeeModel.getEmail(),
                employeeModel.getAddress(),
                employeeModel.getRole(),
                credentialService.mapModelToEntity(employeeModel.getCredential())
        );

        employeeEntity.getCredentials().setEmployee(employeeEntity);
        return employeeEntity;
    }

    protected EmployeeModel mapEntityToModel(Employee employeeEntity) {
        EmployeeModel employeeModel = new EmployeeModel(
                employeeEntity.getEmployeeId(),
                employeeEntity.getFirstName(),
                employeeEntity.getLastName(),
                employeeEntity.getPhone(),
                employeeEntity.getEmail(),
                employeeEntity.getAddress(),
                employeeEntity.getRole(),
                credentialService.mapEntityToModel(employeeEntity.getCredentials())
        );

        employeeModel.getCredential().setEmployee(employeeModel);
        return employeeModel;
    }
}
