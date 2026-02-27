package com.spotevent.user.infrastructure.spi;

import com.spotevent.user.domain.model.User;
import com.spotevent.user.domain.persistence.UserRepository;
import com.spotevent.user.infrastructure.mapper.UserMapper;
import com.spotevent.user.infrastructure.spi.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserSpi {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Integer getUserIdByLogin(String login) {
        return userRepository.findByUsername(login)
                .map(User::getId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé : " + login));
    }

    public UserDto getUserById(Integer id) {
        return userRepository.findById(id)
                .map(userMapper::toSpiDto)
                .orElseThrow(() -> new RuntimeException("Utilisateur ID " + id + " introuvable"));
    }

    public List<UserDto> getUsersByIds(Set<Integer> ids) {
        if (ids == null || ids.isEmpty()) return List.of();

        return userRepository.findAllById(ids).stream()
                .map(userMapper::toSpiDto)
                .collect(Collectors.toList());
    }
}
