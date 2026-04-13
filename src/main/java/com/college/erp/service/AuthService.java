package com.college.erp.service;

import com.college.erp.dto.AuthRequest;
import com.college.erp.dto.AuthResponse;
import com.college.erp.entity.User;

public interface AuthService {

    AuthResponse register(AuthRequest authRequest);

    AuthResponse login(AuthRequest request);
}