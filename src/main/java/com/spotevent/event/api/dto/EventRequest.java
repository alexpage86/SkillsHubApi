package com.spotevent.event.api.dto;

import com.spotevent.keyword.domain.model.Keyword;
import com.spotevent.user.domain.model.User;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class EventRequest {

    private String name;

    private String description;

    private LocalDate date;

    private LocalTime time;

    private Double longitude;

    private Double latitude;

    private boolean privateEvent;

    private User creator;

    private List<Keyword> interests;

    private List<User> users;
}
