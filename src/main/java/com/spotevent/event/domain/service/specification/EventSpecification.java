package com.spotevent.event.domain.service.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.spotevent.event.domain.model.filter.EventFilter;
import com.spotevent.event.domain.model.Event;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

public class EventSpecification {

    private EventSpecification() {
        /* This utility class should not be instantiated */
    }

    public static Specification<Event> byFilter(EventFilter filter) {
        return (root, query, cb) -> {

            query.distinct(true);

            List<Predicate> predicates = new ArrayList<>();

            // Filtre sur la date
            if (filter.from() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("date"), filter.from()));
            }
            if (filter.to() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("date"), filter.to()));
            }

            // Géolocalisation
            if (filter.latMin() != null && filter.latMax() != null) {
                predicates.add(cb.between(root.get("latitude"), filter.latMin(), filter.latMax()));
            }
            if (filter.lngMin() != null && filter.lngMax() != null) {
                predicates.add(cb.between(root.get("longitude"), filter.lngMin(), filter.lngMax()));
            }

            // Intérêts
            if (filter.interests() != null && !filter.interests().isEmpty()) {
                // 1. On join la collection d'entiers (Integer)
                Join<Event, Integer> interestJoin = root.join("interestIds");

                // 2. Le join représente ici directement la valeur de l'ID
                // On peut donc utiliser .in() directement sur le join
                predicates.add(interestJoin.in(filter.interests()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}