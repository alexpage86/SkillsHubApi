package com.spotevent.rest.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.spotevent.rest.entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer>, JpaSpecificationExecutor<Event> {
	
	
	Optional<Event> findById(Integer id);
	
	/**
	 * To get events for a date
	 * @param date
	 * @return events on the date parameter
	 */
	List<Event> findByDate(LocalDate date);
	
	/**
	 * To get events on the date and after
	 * @param date
	 * @return events on the date parameter and after
	 */
	List<Event> findByDateGreaterThanEqual(LocalDate date);
	
	/**
	 * To get events for day
	 * @param date start, date end
	 * @return events between the dates in parameters
	 */
	List<Event> findByDateBetween(LocalDate start, LocalDate end);
	
	/**
	 * To get events before day (past)
	 * @param date
	 * @return events before date in parameter
	 */
	List<Event> findByDateLessThanEqual(LocalDate date);
	
	List<Event> findByDateBetweenAndLatitudeBetweenAndLongitudeBetween(
	    LocalDate from, LocalDate to, double latMin, double latMax, double lngMin, double lngMax
	);

	List<Event> findByDateGreaterThanEqualAndLatitudeBetweenAndLongitudeBetween(
	    LocalDate from, double latMin, double latMax, double lngMin, double lngMax
	);

	List<Event> findByDateLessThanEqualAndLatitudeBetweenAndLongitudeBetween(
	    LocalDate to, double latMin, double latMax, double lngMin, double lngMax
	);

	List<Event> findByLatitudeBetweenAndLongitudeBetween(
	    double latMin, double latMax, double lngMin, double lngMax
	);
	
	List<Event> findDistinctByDateBetweenAndLatitudeBetweenAndLongitudeBetweenAndInterests_IdIn(
	    LocalDate from, LocalDate to, double latMin, double latMax, double lngMin, double lngMax, List<Integer> interestIds
	);

	List<Event> findDistinctByDateGreaterThanEqualAndLatitudeBetweenAndLongitudeBetweenAndInterests_IdIn(
	    LocalDate from, double latMin, double latMax, double lngMin, double lngMax, List<Integer> interestIds
	);

	List<Event> findDistinctByDateLessThanEqualAndLatitudeBetweenAndLongitudeBetweenAndInterests_IdIn(
	    LocalDate to, double latMin, double latMax, double lngMin, double lngMax, List<Integer> interestIds
	);

	List<Event> findDistinctByLatitudeBetweenAndLongitudeBetweenAndInterests_IdIn(
	    double latMin, double latMax, double lngMin, double lngMax, List<Integer> interestIds
	);
	
	List<Event> findByInterests_IdIn(
			List<Integer> interestIds
	);
	
	List<Event> findDistinctByDateLessThanEqualAndInterests_IdIn(LocalDate date, List<Integer> interestId);
	
	List<Event> findDistinctByDateGreaterThanEqualAndInterests_IdIn(LocalDate date, List<Integer> interestIds);
	
	List<Event> findDistinctByDateBetweenAndInterests_IdIn(LocalDate start, LocalDate end, List<Integer> interestIds);
}

