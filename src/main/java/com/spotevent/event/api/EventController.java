package com.spotevent.event.api;

import com.spotevent.event.api.dto.EventRequest;
import com.spotevent.event.api.dto.EventResponse;
import com.spotevent.event.infrastructure.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.spotevent.event.domain.model.filter.EventFilter;
import com.spotevent.event.domain.model.Event;
import com.spotevent.event.infrastructure.modelassembler.EventModelAssembler;
import com.spotevent.event.domain.service.EventService;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

	private final EventService eventService;
	private final EventModelAssembler assembler;
    private final EventMapper eventMapper;

    @PostMapping("/search")
    public ResponseEntity<Page<EntityModel<EventResponse>>> searchEvents(
            @RequestBody EventFilter filter,
            Pageable pageable) {
        Page<Event> events = eventService.findByFilter(filter, pageable);
        return ResponseEntity.ok(events.map(assembler::toModel));
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<EntityModel<EventResponse>> getEventById(@PathVariable Integer id) {
        Event event = eventService.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found with id : " + id)
        );
        return ResponseEntity.ok(assembler.toModel(event));
    }
    
    @PostMapping
    public ResponseEntity<EntityModel<EventResponse>> createEvent(@RequestBody EventRequest eventRequest) {
        Event eventToCreate = eventMapper.toEntity(eventRequest);
        Event savedEvent = eventService.save(eventToCreate);

        EntityModel<EventResponse> entityModel = assembler.toModel(savedEvent);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<EventResponse>> updateEvent(
            @PathVariable Integer id,
            @RequestBody EventRequest eventRequest) {

        Event eventChanges = eventMapper.toEntity(eventRequest);
        Event savedEvent = eventService.updateEvent(id, eventChanges);
        return ResponseEntity.ok(assembler.toModel(savedEvent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Integer id) {
        if (!eventService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
        eventService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
