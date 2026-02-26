package com.spotevent.project.infrastructure.ports;

import com.spotevent.project.domain.model.UserModel;
import com.spotevent.project.domain.service.UserService;
import com.spotevent.project.infrastructure.mapper.ProjectUserMapper;
import com.spotevent.user.infrastructure.spi.UserSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserSpi userSpi;
    private final ProjectUserMapper projectUserMapper;

    @Override
    public Integer getUserIdByLogin(String login) {
        return userSpi.getUserIdByLogin(login);
    }

    @Override
    public UserModel getUserById(Integer id) {
        return projectUserMapper.map(userSpi.getUserById(id));
    }

    @Override
    public List<UserModel> getUsersByIds(Set<Integer> ids) {
        return userSpi.getUsersByIds(ids).stream().map(projectUserMapper::map).collect(Collectors.toList());
    }
}
