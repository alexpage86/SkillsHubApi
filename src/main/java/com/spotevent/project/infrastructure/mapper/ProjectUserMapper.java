package com.spotevent.project.infrastructure.mapper;

import com.spotevent.project.api.dto.UserResponse;
import com.spotevent.project.domain.model.UserModel;
import com.spotevent.shared.mapper.GlobalMapperConfig;
import com.spotevent.user.infrastructure.spi.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface ProjectUserMapper {
    UserModel map(UserDto dto);
    UserResponse map(Integer value);
}
