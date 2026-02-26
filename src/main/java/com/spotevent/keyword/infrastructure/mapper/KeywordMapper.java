package com.spotevent.keyword.infrastructure.mapper;

import com.spotevent.keyword.api.dto.KeywordRequest;
import com.spotevent.keyword.api.dto.KeywordResponse;
import com.spotevent.keyword.domain.model.Keyword;
import com.spotevent.keyword.infrastructure.spi.dto.KeywordDto;
import com.spotevent.shared.mapper.GlobalMapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface KeywordMapper {

    Keyword toEntity(KeywordRequest keywordRequest);

    KeywordResponse toResponse(Keyword keyWord);

    KeywordDto toSpiDto(Keyword keyWord);
}
