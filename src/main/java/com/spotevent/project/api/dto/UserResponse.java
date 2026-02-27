package com.spotevent.project.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public record UserResponse (
        Integer id,
        String firstName,
        String lastName,
        String username,
        String password,
        String email,
        @JsonFormat(pattern="yyyy-MM-dd")
        Date birthDate,
        List<Integer> interestIds,
        String photoProfileId
)
{}
