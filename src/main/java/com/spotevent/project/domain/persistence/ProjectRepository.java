package com.spotevent.project.domain.persistence;

import com.spotevent.project.domain.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {}
