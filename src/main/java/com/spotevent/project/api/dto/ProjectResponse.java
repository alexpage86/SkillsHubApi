package com.spotevent.project.api.dto;

import java.util.List;

public record ProjectResponse(
        Integer id,
        String title,
        String description,
        String location,
        UserResponse owner,
        List<KeywordResponse> requiredSkills,
        List<UserResponse> attendees
) {}
