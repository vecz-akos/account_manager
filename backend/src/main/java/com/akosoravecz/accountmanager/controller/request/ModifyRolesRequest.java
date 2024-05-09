package com.akosoravecz.accountmanager.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ModifyRolesRequest {
    @NotBlank(message = "Rolename cannot be empty!")
    private String roleName;
}
