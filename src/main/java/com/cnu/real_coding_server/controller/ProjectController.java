package com.cnu.real_coding_server.controller;

import com.cnu.real_coding_server.entity.Project;
import com.cnu.real_coding_server.model.request.ProjectRequest;
import com.cnu.real_coding_server.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    @PostMapping
    public ResponseEntity<Project> save(@RequestBody ProjectRequest projectRequest){
        return ResponseEntity.ok(projectService.save(projectRequest));
    }
    @GetMapping
    public ResponseEntity<List<Project>> findAllProjects () {
        return ResponseEntity.ok(projectService.findAll());
    }
    @GetMapping("/{projectId}")
    public ResponseEntity<Project> findProjectById(@PathVariable("projectId") Integer projectId){
        return ResponseEntity.ok(projectService.findById(projectId).orElse(null));
    }
    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject (@PathVariable("projectId") Integer projectId, @RequestBody ProjectRequest projectRequest){
        return ResponseEntity.ok(projectService.updateProject(projectId, projectRequest).orElse(null));
    }
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject (@PathVariable("projectId") Integer projectId){
        projectService.delete(projectId);
        return ResponseEntity.noContent().build();
    }
}
