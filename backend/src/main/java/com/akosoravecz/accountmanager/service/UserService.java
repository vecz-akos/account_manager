package com.akosoravecz.accountmanager.service;

import com.akosoravecz.accountmanager.dto.model.UserDto;

public interface UserService {
    UserDto register(UserDto userDto);

    boolean delete(UserDto userDto);
}
