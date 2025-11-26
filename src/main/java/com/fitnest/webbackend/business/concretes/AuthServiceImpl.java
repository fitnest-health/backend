package com.fitnest.webbackend.business.concretes;

import com.fitnest.webbackend.security.jwt.JwtService;
import com.fitnest.webbackend.business.abstracts.AuthService;
import com.fitnest.webbackend.model.entities.User;
import com.fitnest.webbackend.model.enums.AppRole;
import com.fitnest.webbackend.payload.dtos.auth.AuthResponse;
import com.fitnest.webbackend.payload.dtos.auth.LoginRequest;
import com.fitnest.webbackend.payload.dtos.auth.ProfileResponse;
import com.fitnest.webbackend.payload.dtos.auth.RegisterRequest;
import com.fitnest.webbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(CONFLICT, "Email already in use");
        }

        User user = new User(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.firstName(),
                request.lastName(),
                AppRole.ROLE_USER
        );

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return AuthResponse.bearer(token);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(principal);
        return AuthResponse.bearer(token);
    }

    @Override
    public ProfileResponse profile(UserDetails userDetails) {
        if (userDetails == null) {
            throw new ResponseStatusException(UNAUTHORIZED, "No authenticated user");
        }
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(CONFLICT, "User missing"));
        return new ProfileResponse(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole()
        );
    }
}

