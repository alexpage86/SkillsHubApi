package com.spotevent.rest.modelassembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.spotevent.rest.controller.EventController;
import com.spotevent.rest.entity.Event;

@Component
public class EventModelAssembler implements RepresentationModelAssembler<Event, EntityModel<Event>> {

	@Override
	public EntityModel<Event> toModel(Event event) {
		return EntityModel.of(event,
	            linkTo(methodOn(EventController.class).getEventById(event.getId())).withSelfRel(),
	            linkTo(methodOn(EventController.class).searchEvents(null)).withRel("events")
	        );
	}

}
