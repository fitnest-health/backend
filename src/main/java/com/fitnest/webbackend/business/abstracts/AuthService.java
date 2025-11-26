package com.fitnest.webbackend.business.abstracts;

import com.fitnest.webbackend.payload.dtos.auth.AuthResponse;
import com.fitnest.webbackend.payload.dtos.auth.LoginRequest;
import com.fitnest.webbackend.payload.dtos.auth.ProfileResponse;
import com.fitnest.webbackend.payload.dtos.auth.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    ProfileResponse profile(UserDetails userDetails);
}


