package com.spotevent.event.domain.service;

import com.spotevent.event.infrastructure.mapper.EventMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spotevent.event.domain.model.filter.EventFilter;
import com.spotevent.event.domain.model.Event;
import com.spotevent.event.domain.persistence.EventRepository;
import com.spotevent.event.domain.service.specification.EventSpecification;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
	
	private final EventRepository eventRepository;
	private final EventMapper eventMapper;

	@Transactional(readOnly = true)
	public Page<Event> findByFilter(EventFilter filter, Pageable pageable){
		Specification<Event> spec = EventSpecification.byFilter(filter);
	    return eventRepository.findAll(spec, pageable);
	}

	@Transactional(readOnly = true)
	public Optional<Event> findById(Integer id) {
		return Optional.of(eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id : " + id)));
	}

	@Transactional
	public Event save(Event event) {
		return eventRepository.save(event);
	}

	public boolean existsById(Integer id) {
		return eventRepository.existsById(id);
	}

	@Transactional
	public void deleteById(Integer id) {
		Event event = eventRepository.findById(id).orElseThrow();
		eventRepository.delete(event);
	}

	@Transactional
	public Event updateEvent(Integer id, Event eventChanges){
		// 1. Vérifier l'existence et récupérer l'état actuel (fail-fast)
		Event existingEvent = eventRepository.findById(id).orElseThrow();
		// 2. Mettre à jour uniquement les champs autorisés
		eventMapper.updateEntityFromDomain(eventChanges, existingEvent);

		// 3. Sauvegarder l'objet fusionné
		return eventRepository.save(existingEvent);
	}
}
