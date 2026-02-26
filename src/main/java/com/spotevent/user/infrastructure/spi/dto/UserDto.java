package com.spotevent.user.infrastructure.spi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public record UserDto (
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
){}
