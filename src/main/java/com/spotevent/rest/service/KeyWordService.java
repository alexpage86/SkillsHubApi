package com.spotevent.rest.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.spotevent.rest.entity.KeyWord;
import com.spotevent.rest.repository.KeyWordRepository;

@Service
public class KeyWordService {
	
	private final KeyWordRepository keywordRepository;

    public KeyWordService(KeyWordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public List<KeyWord> search(String search) {
        if (search == null || search.isBlank()) {
            return keywordRepository.findAll(PageRequest.of(0, 20)).getContent();
        }
        return keywordRepository.findByLabelContainingIgnoreCase(search);
    }
    
    public KeyWord save(KeyWord newKeyword) {
    	return keywordRepository.save(newKeyword);
    }

}
