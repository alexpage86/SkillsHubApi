package com.spotevent.user.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="t_users")
@Getter
@Setter
@RequiredArgsConstructor
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@JsonView
	private String firstName;
	
	@Column
	@JsonView
	private String lastName;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column(name = "e_mail")
	private String email;
	
	@Column
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthDate;

	@ElementCollection
	@CollectionTable(name = "tj_users_interests", joinColumns = @JoinColumn(name = "fk_user_id"))
	@Column(name = "fk_interest_id")
	private List<Integer> interestIds;

	// Id du document Mongo (Photo)
    private String photoProfileId;
	
	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", birthDate='" + birthDate + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				'}';
	}

}
