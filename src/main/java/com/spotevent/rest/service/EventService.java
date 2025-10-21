package com.spotevent.rest.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.spotevent.rest.dto.EventFilterRequest;
import com.spotevent.rest.entity.Event;
import com.spotevent.rest.repository.EventRepository;
import com.spotevent.rest.specification.EventSpecification;

@Service
public class EventService {
	
	private final EventRepository eventRepository;
	
	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	public List<Event> findByFilter(EventFilterRequest filter){
		Specification<Event> spec = EventSpecification.byFilter(filter);
	    return eventRepository.findAll(spec);
	}

	public Event findById(Integer id) {
		return eventRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
	}

	public Event save(Event event) {
		return eventRepository.save(event);
	}

	public boolean existsById(Integer id) {
		return eventRepository.existsById(id);
	}

	public void deleteById(Integer id) {
		eventRepository.deleteById(id);
	}
}
