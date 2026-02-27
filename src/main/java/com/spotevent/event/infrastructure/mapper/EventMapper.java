package com.spotevent.event.infrastructure.mapper;

import com.spotevent.event.api.dto.EventRequest;
import com.spotevent.event.api.dto.EventResponse;
import com.spotevent.event.domain.model.Event;
import com.spotevent.shared.mapper.GlobalMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = GlobalMapperConfig.class)
public interface EventMapper {

    /**
     * Transforme l'entité Event en sa réponse http
     * @param event
     * @return
     */
    EventResponse toResponse(Event event);

    /**
     * Transforme la requête http en entité Event
     * @param eventResponse
     * @return
     */
    @Mapping(target = "id", ignore = true)
    Event toEntity(EventRequest eventResponse);

    EventRequest toRequest(Event event);

    // Mise à jour d'une entité existante (Pour les @PutMapping)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(EventRequest request, @MappingTarget Event event);

    void updateEntityFromDomain(Event eventChanges, @MappingTarget Event existingEvent);
}
