package com.spotevent.rest.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="t_keyword")
public class KeyWord implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String label;
	
//	@ManyToMany
//	@JsonIgnore
//	@JoinTable( name = "tj_users_interests",
//				joinColumns = @JoinColumn( name = "fk_interest_id", referencedColumnName="id"),
//				inverseJoinColumns = @JoinColumn( name = "fk_user_id", referencedColumnName="id"))
//	@JsonManagedReference
//	private List<User> users;
//	
//	@ManyToMany
//	@JsonIgnore
//	@JoinTable( name = "tj_events_interests",
//				joinColumns = @JoinColumn( name = "fk_interest_id", referencedColumnName="id"),
//				inverseJoinColumns = @JoinColumn( name = "fk_event_id", referencedColumnName="id"))
//	private List<Event> events;

	public KeyWord() {}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
//	public List<User> getUsers(){
//		return users;
//	}
//	
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}
//	
//	public List<Event> getEvents(){
//		return events;
//	}
//	
//	public void setEvents(List<Event> events){
//		this.events = events;
//	}
	
	@Override
	public String toString() {
		return "KeyWord{" +
				"id=" + id +
				", label='" + label + '\'' +
				'}';
	}
}
