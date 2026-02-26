package com.spotevent.keyword.domain.persistence;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spotevent.keyword.domain.model.Keyword;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
	
	List<Keyword> findByLabelContainingIgnoreCase(String search);
	
}
