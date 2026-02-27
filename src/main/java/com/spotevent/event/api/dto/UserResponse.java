package com.spotevent.event.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public record UserResponse(
        Integer id,
        String firstName,
        String lastName,
        String username,
        String eMail,
        @JsonFormat(pattern="yyyy-MM-dd")
        Date birthDate,
        List<KeywordResponse> interests,
        String photoProfileId
) {}
