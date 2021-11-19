package com.ibm.taskManager.repository;

import com.ibm.taskManager.entity.EmbeddedId.EmployeeTaskId;
import com.ibm.taskManager.entity.EmployeeTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeTaskRepository extends JpaRepository<EmployeeTask, EmployeeTaskId> {
    @Query(value = "Select * from employee_task where employee_id = :#{#emplId} and task_id = :#{#taskId}", nativeQuery = true)
    public List<EmployeeTask> findEmployeeTask(@Param("emplId") Long employeeId, @Param("taskId") Long taskId);
}
