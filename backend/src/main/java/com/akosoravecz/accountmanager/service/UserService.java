package com.akosoravecz.accountmanager.service;

import com.akosoravecz.accountmanager.controller.request.RegisterRequest;
import com.akosoravecz.accountmanager.dto.model.UserDto;

import java.util.Collection;

public interface UserService {
    UserDto register(RegisterRequest userDto);

    String loginUser(String username, String password);

    Collection<UserDto> getAllUser();

    boolean delete(UserDto userDto);
}
