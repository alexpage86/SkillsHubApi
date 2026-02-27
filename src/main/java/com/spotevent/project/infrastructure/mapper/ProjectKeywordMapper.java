package com.spotevent.project.infrastructure.mapper;

import com.spotevent.keyword.infrastructure.spi.dto.KeywordDto;
import com.spotevent.project.api.dto.KeywordResponse;
import com.spotevent.project.domain.model.KeywordModel;
import com.spotevent.project.domain.service.KeywordService;
import com.spotevent.shared.mapper.GlobalMapperConfig;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(config = GlobalMapperConfig.class, uses = KeywordService.class)
public interface ProjectKeywordMapper {
        KeywordModel map(KeywordDto dto);
        KeywordResponse map(Integer value);
        List<KeywordResponse> map(Set<Integer> value);
}
