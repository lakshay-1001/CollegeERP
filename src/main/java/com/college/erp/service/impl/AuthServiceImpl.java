package com.college.erp.service.impl;

import com.college.erp.dto.AuthRequest;
import com.college.erp.dto.AuthResponse;
import com.college.erp.entity.User;
import com.college.erp.entity.enums.UserStatus;
import com.college.erp.repository.UserRepository;
import com.college.erp.security.JwtUtil;
import com.college.erp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public User register(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.PENDING);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // ✅ Allow login even if PENDING
        String token = jwtUtil.generateToken(user.getEmail());

        String message = "Login successful";

        if (user.getStatus() == UserStatus.PENDING) {
            message = "Login successful (Limited Access - Pending Approval)";
        }

        return new AuthResponse(
                token,
                message,
                user.getEmail(),
                user.getRole(),
                user.getStatus()
        );
    }
}