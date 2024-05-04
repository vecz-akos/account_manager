package com.akosoravecz.accountmanager.dto.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RoleDto {
    private String name;
    private String description;
}
