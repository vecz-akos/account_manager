package com.akosoravecz.accountmanager.controller.api;

import com.akosoravecz.accountmanager.controller.request.LoginRequest;
import com.akosoravecz.accountmanager.controller.request.ModifyRolesRequest;
import com.akosoravecz.accountmanager.controller.request.RegisterRequest;
import com.akosoravecz.accountmanager.dto.model.user.UserDto;
import com.akosoravecz.accountmanager.dto.response.LoginResponse;
import com.akosoravecz.accountmanager.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid RegisterRequest registerRequest) {
        UserDto newUser = userService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        String token = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse(token));
    }

    @GetMapping("/all_user")
    public ResponseEntity<Collection<UserDto>> allUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }

    @PostMapping("/modify_role/add")
    public ResponseEntity<UserDto> addRole(@RequestBody @Valid ModifyRolesRequest modifyRolesRequest) {
        UserDto userDto = userService.addRole(modifyRolesRequest.getUsername(), modifyRolesRequest.getRoleName());
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }
}
