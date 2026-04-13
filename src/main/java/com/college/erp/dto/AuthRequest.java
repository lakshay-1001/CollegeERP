package com.college.erp.dto;

import com.college.erp.entity.enums.Role;
import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
    private String name;
    private Role role;
}
