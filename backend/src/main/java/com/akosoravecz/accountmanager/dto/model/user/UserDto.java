package com.akosoravecz.accountmanager.dto.model.user;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDto {
    private String name;
    private String username;
    private String password;
    private Collection<RoleDto> roles;
}
