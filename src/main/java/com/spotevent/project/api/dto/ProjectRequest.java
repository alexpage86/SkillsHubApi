package com.spotevent.project.api.dto;

import java.util.Set;

public record ProjectRequest(
        String title,
        String description,
        String location,
        Set<Integer> requiredSkillIds
) {}
