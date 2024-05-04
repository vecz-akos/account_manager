package com.akosoravecz.accountmanager.dto.mapper;

import com.akosoravecz.accountmanager.dto.model.RoleDto;
import com.akosoravecz.accountmanager.model.user.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public static RoleDto toRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setName(role.getName());
        roleDto.setDescription(role.getDescription());
        return roleDto;
    }
}
