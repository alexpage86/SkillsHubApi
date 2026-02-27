package com.spotevent.keyword.infrastructure.spi;

import com.spotevent.keyword.domain.model.Keyword;
import com.spotevent.keyword.domain.service.KeywordService;
import com.spotevent.keyword.infrastructure.mapper.KeywordMapper;
import com.spotevent.keyword.infrastructure.spi.dto.KeywordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KeywordSpi {
    private final KeywordService keyWordService;
    private final KeywordMapper keywordMapper;

    public List<KeywordDto> getKeywordsByIds(List<Integer> ids){
        List<Keyword> keywords = keyWordService.findAll(ids);
        return keywords.stream().map(keywordMapper::toSpiDto).collect(Collectors.toList());
    }
}
