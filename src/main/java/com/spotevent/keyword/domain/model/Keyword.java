package com.spotevent.keyword.domain.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="t_keyword")
@Getter @Setter
@RequiredArgsConstructor
public class Keyword implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String label;
	
	@Override
	public String toString() {
		return "KeyWord{" +
				"id=" + id +
				", label='" + label + '\'' +
				'}';
	}
}
