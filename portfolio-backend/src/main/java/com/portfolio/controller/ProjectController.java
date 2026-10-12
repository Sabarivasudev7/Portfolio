package com.portfolio.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.portfolio.entity.Project;
import com.portfolio.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public List<Project> getProjects() {
        return service.getAllProjects();
    }

    @PostMapping
    public Project addProject(@RequestBody Project project) {
        return service.saveProject(project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project project) {
        return service.updateProject(id, project);
    }

    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable Long id) {
        boolean deleted = service.deleteProject(id);
        return deleted ? "Project deleted successfully" : "Project not found";
    }
}
