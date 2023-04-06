package com.cnu.real_coding_server.repository;

import com.cnu.real_coding_server.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProjectRepository extends JpaRepository<Project, Integer>{
    //Project save(Project project);
    //List<Project> findAll();
    //Optional<Project> findById(Integer projectId);
    //void delete(Project project);
}
