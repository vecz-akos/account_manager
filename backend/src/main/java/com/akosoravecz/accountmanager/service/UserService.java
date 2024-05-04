package com.akosoravecz.accountmanager.service;

import com.akosoravecz.accountmanager.dto.model.UserDto;

import java.util.Collection;

public interface UserService {
    UserDto register(UserDto userDto);

    Collection<UserDto> getAllUser();

    boolean delete(UserDto userDto);
}
