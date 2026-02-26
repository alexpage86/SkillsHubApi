package com.spotevent.keyword.api;

import java.util.List;
import java.util.stream.Collectors;

import com.spotevent.keyword.api.dto.KeywordRequest;
import com.spotevent.keyword.api.dto.KeywordResponse;
import com.spotevent.keyword.infrastructure.mapper.KeywordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spotevent.keyword.domain.model.Keyword;
import com.spotevent.keyword.domain.service.KeywordService;

@RestController
@RequestMapping("/keyword")
@RequiredArgsConstructor
public class KeywordController {
	
	private final KeywordService keywordService;
    private final KeywordMapper keywordMapper;

    @GetMapping
    public ResponseEntity<List<KeywordResponse>> searchKeywords(@RequestParam(required = false) String search) {
        List<Keyword> keywords = keywordService.search(search);
        List<KeywordResponse> response = keywords.stream().map(keywordMapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }
    
    @PostMapping
    public ResponseEntity<KeywordResponse> createKeyword(@RequestBody KeywordRequest keywordRequest) {
        Keyword keyword = keywordMapper.toEntity(keywordRequest);
        Keyword savedKeyword =  keywordService.save(keyword);
        return ResponseEntity.ok().body(keywordMapper.toResponse(savedKeyword));
    }

}
