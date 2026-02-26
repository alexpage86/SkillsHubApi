package com.spotevent.project.domain.service;

import com.spotevent.project.domain.model.Project;
import com.spotevent.project.domain.persistence.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public Project save(Project project) { return projectRepository.save(project); }
    public Project findById(Integer id) {
        return projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Projet introuvable"));
    }
}
