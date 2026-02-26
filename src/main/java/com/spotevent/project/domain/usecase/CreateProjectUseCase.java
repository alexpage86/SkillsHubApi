package com.spotevent.project.domain.usecase;

import com.spotevent.project.domain.model.Project;
import com.spotevent.project.domain.service.ProjectService;
import com.spotevent.project.domain.service.UserService;
import com.spotevent.shared.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateProjectUseCase {
    private final ProjectService projectService;
    private final SecurityUtils securityUtils;
    private final UserService userProvider;

    @Transactional
    public Project execute(Project newProject) {
        String login = securityUtils.getCurrentUserLogin().orElseThrow();
        Integer ownerId = userProvider.getUserIdByLogin(login);
        newProject.setOwnerId(ownerId);
        return projectService.save(newProject);
    }
}
