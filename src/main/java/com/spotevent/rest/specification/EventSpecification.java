package com.spotevent.rest.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.spotevent.rest.dto.EventFilterRequest;
import com.spotevent.rest.entity.Event;
import com.spotevent.rest.entity.KeyWord;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EventSpecification {

    public static Specification<Event> byFilter(EventFilterRequest filter) {
        return (Root<Event> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            Predicate predicate = cb.conjunction();

            // Filtre sur la date
            if (filter.getFrom() != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("date"), filter.getFrom()));
            }
            if (filter.getTo() != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("date"), filter.getTo()));
            }

            // Filtre sur les coordonnées
            if (filter.getLatMin() != null && filter.getLatMax() != null) {
                predicate = cb.and(predicate, cb.between(root.get("latitude"), filter.getLatMin(), filter.getLatMax()));
            }
            if (filter.getLngMin() != null && filter.getLngMax() != null) {
                predicate = cb.and(predicate, cb.between(root.get("longitude"), filter.getLngMin(), filter.getLngMax()));
            }

            // Filtre sur les intérêts
            List<KeyWord> interests = filter.getInterests();
            if (interests != null && !interests.isEmpty()) {
                Join<Event, KeyWord> join = root.join("interests");
                CriteriaBuilder.In<Integer> inClause = cb.in(join.get("id"));
                interests.forEach(k -> inClause.value(k.getId()));
                predicate = cb.and(predicate, inClause);
            }
            return predicate;
        };
    }
}