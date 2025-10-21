package com.spotevent.rest.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.spotevent.rest.dto.EventFilterRequest;
import com.spotevent.rest.entity.Event;
import com.spotevent.rest.modelassembler.EventModelAssembler;
import com.spotevent.rest.service.EventService;

@RestController
public class EventController {

	private final EventService eventService;
	private final EventModelAssembler assembler;
	
	public EventController(EventService eventService, EventModelAssembler assembler) {
        this.eventService = eventService;
        this.assembler = assembler;
    }
	
	@PostMapping("/events/search")
	public CollectionModel<EntityModel<Event>> searchEvents(@RequestBody EventFilterRequest filter) {
		List<Event> events = eventService.findByFilter(filter);
		
		List<EntityModel<Event>> eventModels = events.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(eventModels,
                WebMvcLinkBuilder.linkTo(methodOn(EventController.class).searchEvents(filter)).withSelfRel());
	}
	
	@GetMapping("/events/{id}")
    public EntityModel<Event> getEventById(@PathVariable Integer id) {
        Event event = eventService.findById(id);
        return assembler.toModel(event);
    }
    
    @PostMapping("/events")
    public ResponseEntity<EntityModel<Event>> createEvent(@RequestBody Event event) throws URISyntaxException {
        Event savedEvent = eventService.save(event);
        EntityModel<Event> entityModel = assembler.toModel(savedEvent);

        return ResponseEntity
                .created(new URI(entityModel.getRequiredLink("self").getHref()))
                .body(entityModel);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<EntityModel<Event>> updateEvent(
            @PathVariable Integer id,
            @RequestBody Event updatedEvent) {
        Event event = eventService.findById(id);

        // on met à jour les champs pertinents
        event.setName(updatedEvent.getName());
        event.setDescription(updatedEvent.getDescription());
        event.setDate(updatedEvent.getDate());
        event.setLatitude(updatedEvent.getLatitude());
        event.setLongitude(updatedEvent.getLongitude());
        event.setPrivateEvent(updatedEvent.isPrivateEvent());

        Event savedEvent = eventService.save(event);
        return ResponseEntity.ok(assembler.toModel(savedEvent));
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Integer id) {
        if (!eventService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
        eventService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
