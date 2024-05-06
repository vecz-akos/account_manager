package com.akosoravecz.accountmanager.dto.mapper;

import com.akosoravecz.accountmanager.dto.model.RoleDto;
import com.akosoravecz.accountmanager.dto.model.UserDto;
import com.akosoravecz.accountmanager.model.user.Role;
import com.akosoravecz.accountmanager.model.user.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setRoles(
                new HashSet<RoleDto>(
                        user.getRoles().stream()
                                .map(RoleMapper::toRoleDto)
                                .collect(Collectors.toSet())));
        return userDto;
    }
}
