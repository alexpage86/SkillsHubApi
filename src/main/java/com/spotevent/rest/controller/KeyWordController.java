package com.spotevent.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spotevent.rest.dto.KeyWordDTO;
import com.spotevent.rest.entity.KeyWord;
import com.spotevent.rest.service.KeyWordService;

@RestController
public class KeyWordController {
	
	private final KeyWordService keywordService;

    public KeyWordController(KeyWordService keywordService) {
        this.keywordService = keywordService;
    }

    @GetMapping("/tags")
    public List<KeyWordDTO> searchKeywords(@RequestParam(required = false) String search) {
        return keywordService.search(search).stream()
        		.map(k -> new KeyWordDTO(k.getId(), k.getLabel()))
                .toList();
    }
    
    @PostMapping("/keywords")
    public KeyWord createKeyword(@RequestBody KeyWordDTO dto) {
        KeyWord kw = new KeyWord();
        kw.setLabel(dto.label());
        return keywordService.save(kw);
    }

}
