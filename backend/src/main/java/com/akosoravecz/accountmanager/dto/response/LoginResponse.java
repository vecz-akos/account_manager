package com.akosoravecz.accountmanager.dto.response;


import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class LoginResponse {
    @NonNull
    private String token;
}
