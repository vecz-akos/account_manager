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

    @DeleteMapping("/all_user/{username}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable(value="username") String username) {
        System.out.println(username);
        boolean isDeleted = userService.delete(username);
        return ResponseEntity.status(HttpStatus.OK).body(isDeleted);
    }

    @PostMapping("/all_user/{username}/role")
    public ResponseEntity<UserDto> addRole(@PathVariable(value="username") String username, @RequestBody @Valid ModifyRolesRequest modifyRolesRequest) {
        UserDto userDto = userService.addRole(username, modifyRolesRequest.getRoleName());
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @DeleteMapping("/all_user/{username}/role")
    public ResponseEntity<UserDto> deleteRole(@PathVariable(value="username") String username, @RequestBody @Valid ModifyRolesRequest modifyRolesRequest) {
        UserDto userDto = userService.removeRole(username, modifyRolesRequest.getRoleName());
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }
}
