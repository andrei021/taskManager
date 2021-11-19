package com.ibm.taskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ibm.taskManager.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "Select max(employee_id) from employees", nativeQuery = true)
    public Long findMaxEmployeeId();
}