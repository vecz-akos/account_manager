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
    private Collection<RoleDto> roles;
}
