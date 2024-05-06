package com.akosoravecz.accountmanager.controller.request;

import com.akosoravecz.accountmanager.model.user.Role;
import com.akosoravecz.accountmanager.validations.annotations.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Username cannot be empty!")
    private String name;


    @UniqueUsername
    @NotBlank(message = "Username cannot be empty!")
    private String username;

    @NotBlank(message = "Password cannot be empty!")
    private String password;

    @NotNull(message = "Role cannot be null!")
    private Long role;
}
