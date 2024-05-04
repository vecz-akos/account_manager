package com.akosoravecz.accountmanager.controller.api;

import com.akosoravecz.accountmanager.dto.model.UserDto;
import com.akosoravecz.accountmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public UserDto register(UserDto user) {
        return userService.register(user);
    }

    @GetMapping("/all_user")
    public Collection<UserDto> allUser() {
        return userService.getAllUser();
    }
}
