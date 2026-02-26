package com.spotevent.keyword.domain.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.spotevent.keyword.domain.model.Keyword;
import com.spotevent.keyword.domain.persistence.KeywordRepository;

@Service
@RequiredArgsConstructor
public class KeywordService {
	
	private final KeywordRepository keywordRepository;

    public List<Keyword> search(String search) {
        if (search == null || search.isBlank()) {
            return keywordRepository.findAll(PageRequest.of(0, 20)).getContent();
        }
        return keywordRepository.findByLabelContainingIgnoreCase(search);
    }
    
    public Keyword save(Keyword newKeyword) {
    	return keywordRepository.save(newKeyword);
    }

    public List<Keyword> findAll(List<Integer> ids) {
        return keywordRepository.findAllById(ids);
    }
}
