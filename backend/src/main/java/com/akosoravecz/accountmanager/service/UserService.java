package com.akosoravecz.accountmanager.service;

import com.akosoravecz.accountmanager.controller.request.RegisterRequest;
import com.akosoravecz.accountmanager.dto.model.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public interface UserService extends UserDetailsService {
    UserDto register(RegisterRequest userDto);

    String loginUser(String username, String password);

    Collection<UserDto> getAllUser();

    boolean delete(UserDto userDto);

    UserDto addRole(String username, String roleName);

    UserDto removeRole(String username, String roleName);
}
