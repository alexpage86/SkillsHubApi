package com.spotevent.project.infrastructure.ports;

import com.spotevent.keyword.infrastructure.spi.KeywordSpi;
import com.spotevent.project.domain.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeywordServiceImpl implements KeywordService {
    private final KeywordSpi keywordSpi;

}
