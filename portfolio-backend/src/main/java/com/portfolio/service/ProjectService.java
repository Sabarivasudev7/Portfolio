package com.portfolio.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.portfolio.entity.Project;
import com.portfolio.repository.ProjectRepository;

@Service
public class ProjectService {
    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public List<Project> getAllProjects() {
        return repository.findAll();
    }

    public Project saveProject(Project project) {
        return repository.save(project);
    }

    public Project updateProject(Long id, Project updatedProject) {
        return repository.findById(id)
                .map(project -> {
                    project.setTitle(updatedProject.getTitle());
                    project.setDescription(updatedProject.getDescription());
                    project.setTechnologies(updatedProject.getTechnologies());
                    project.setGithubUrl(updatedProject.getGithubUrl());
                    project.setLiveUrl(updatedProject.getLiveUrl());
                    return repository.save(project);
                })
                .orElse(null);
    }

    public boolean deleteProject(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
