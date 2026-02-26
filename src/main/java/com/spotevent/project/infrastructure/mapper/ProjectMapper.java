package com.spotevent.project.infrastructure.mapper;

import com.spotevent.project.api.dto.ProjectRequest;
import com.spotevent.project.api.dto.ProjectResponse;
import com.spotevent.project.domain.model.Project;
import com.spotevent.project.domain.service.KeywordService;
import com.spotevent.project.domain.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { UserService.class, ProjectUserMapper.class, KeywordService.class, ProjectKeywordMapper.class })
public interface ProjectMapper {
    @Mapping(source = "ownerId", target = "owner")
    @Mapping(source = "requiredSkillIds", target = "requiredSkills")
    @Mapping(source = "attendeesIds", target = "attendees")
    ProjectResponse toResponse(Project project);

    Project toEntity(ProjectRequest projectRequest);




}
