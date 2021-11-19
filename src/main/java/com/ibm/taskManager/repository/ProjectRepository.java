package com.ibm.taskManager.repository;

import com.ibm.taskManager.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
