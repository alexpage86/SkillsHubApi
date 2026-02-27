package com.spotevent.project.api;

import com.spotevent.project.api.dto.ProjectRequest;
import com.spotevent.project.api.dto.ProjectResponse;
import com.spotevent.project.domain.model.Project;
import com.spotevent.project.domain.service.ProjectService;
import com.spotevent.project.domain.usecase.CreateProjectUseCase;
import com.spotevent.project.domain.usecase.JoinProjectUseCase;
import com.spotevent.project.infrastructure.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final CreateProjectUseCase createProjectUseCase;
    private final JoinProjectUseCase joinProjectUseCase;
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @PostMapping
    public ResponseEntity<ProjectResponse> create(@RequestBody ProjectRequest projectRequest) {
        Project newProject = projectMapper.toEntity(projectRequest);
        Project savedProject = createProjectUseCase.execute(newProject);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectMapper.toResponse(savedProject));
    }

    @PostMapping("/{id}/join")
    public ResponseEntity<Void> join(@PathVariable Integer id) {
        joinProjectUseCase.execute(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(projectMapper.toResponse(projectService.findById(id)));
    }
}
