package com.spotevent.event.infrastructure.modelassembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.spotevent.event.api.dto.EventResponse;
import com.spotevent.event.domain.model.Event;
import com.spotevent.event.infrastructure.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.spotevent.event.api.EventController;

@Component
@RequiredArgsConstructor
public class EventModelAssembler implements RepresentationModelAssembler<Event, EntityModel<EventResponse>> {

	private final EventMapper eventMapper;

	@Override
	public EntityModel<EventResponse> toModel(Event event) {
		EventResponse response = eventMapper.toResponse(event);

		return EntityModel.of(response,
	            linkTo(methodOn(EventController.class).getEventById(event.getId())).withSelfRel(),
	            linkTo(methodOn(EventController.class).searchEvents(null, null)).withRel("events")
	        );
	}

}
