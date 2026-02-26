package com.spotevent.project.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_projects")
@Getter
@Setter
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private String location;

    @Column(name = "owner_id", nullable = false)
    private Integer ownerId;

    @ElementCollection
    @CollectionTable(name = "tj_projects_skills", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "skill_id")
    private Set<Integer> requiredSkillIds = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "tj_projects_attendees", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "user_id")
    private Set<Integer> attendeesIds = new HashSet<>();
}
