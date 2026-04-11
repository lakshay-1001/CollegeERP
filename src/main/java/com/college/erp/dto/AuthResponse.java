package com.college.erp.dto;

import com.college.erp.entity.enums.Role;
import com.college.erp.entity.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String message;

    private String email;
    private Role role;
    private UserStatus status;
}
