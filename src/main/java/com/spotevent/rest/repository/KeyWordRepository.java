package com.spotevent.rest.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spotevent.rest.entity.KeyWord;

@Repository
public interface KeyWordRepository extends JpaRepository<KeyWord, Integer> {
	
	List<KeyWord> findByLabelContainingIgnoreCase(String search);
	
}
