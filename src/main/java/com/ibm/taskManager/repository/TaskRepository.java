package com.ibm.taskManager.repository;

import com.ibm.taskManager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "Select max(task_id) from tasks", nativeQuery = true)
    public Long findMaxTaskId();
}
