package com.spotevent.event.domain.model.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record EventFilter(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate from,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate to,
        Double latMin,
        Double latMax,
        Double lngMin,
        Double lngMax,
        List<Integer> interests
) {}
