package com.spotevent.event.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_events")
@Getter @Setter
@NoArgsConstructor
public class Event implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate date;
	
	@Column
	@JsonFormat(pattern="HH:mm")
	private LocalTime time;
	
	@Column
	private Double longitude;
	
	@Column
	private Double latitude;
	
	@Column(name = "private_event")
	private boolean privateEvent = false;

	@JoinColumn(name="fk_creator_id", nullable=false)
	private Integer creatorId;

	@ElementCollection
	@CollectionTable(name = "tj_events_interests", joinColumns = @JoinColumn(name = "fk_event_id"))
	@Column(name = "fk_interest_id")
	private List<Integer> interestIds;

	@ElementCollection
	@CollectionTable(name = "tj_events_users", joinColumns = @JoinColumn(name = "fk_event_id"))
	@Column(name = "fk_user_id")
	private List<Integer> participantIds;
}
