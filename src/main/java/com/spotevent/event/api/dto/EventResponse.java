package com.spotevent.event.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record EventResponse (
    Integer id,
    String name,
    String description,
    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate date,
    @JsonFormat(pattern="HH:mm")
    LocalTime time,
    Double longitude,
    Double latitude,
    boolean privateEvent,
    UserResponse creator,
    List<KeywordResponse> interests,
    List<UserResponse> users
) {}
