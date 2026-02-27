package com.spotevent.user.infrastructure.mapper;

import com.spotevent.shared.mapper.GlobalMapperConfig;
import com.spotevent.user.api.dto.UserRequest;
import com.spotevent.user.api.dto.UserResponse;
import com.spotevent.user.domain.model.User;
import com.spotevent.user.infrastructure.spi.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = GlobalMapperConfig.class)
public interface UserMapper {

    /**
     * Transforme l'entité User en sa réponse http
     * @param user
     * @return
     */
    UserResponse toResponse(User user);

    /**
     * Transforme la requête http en entité User
     * @param userRequest
     * @return
     */
    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequest userRequest);

    void updateEntityFromDomain(User userChanges, @MappingTarget User existingUser);

    UserDto toSpiDto(User user);
}
