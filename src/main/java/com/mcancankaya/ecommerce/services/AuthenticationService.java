package com.mcancankaya.ecommerce.services;

import com.mcancankaya.ecommerce.core.mapper.ModelMapperService;
import com.mcancankaya.ecommerce.core.response.CustomResponse;
import com.mcancankaya.ecommerce.core.security.JWTService;
import com.mcancankaya.ecommerce.entities.User;
import com.mcancankaya.ecommerce.entities.enums.Role;
import com.mcancankaya.ecommerce.repositories.UserRepository;
import com.mcancankaya.ecommerce.services.dtos.auth.*;
import com.mcancankaya.ecommerce.services.dtos.response.UserResponse;
import com.mcancankaya.ecommerce.services.rules.UserRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final ModelMapperService modelMapperService;
    private final UserRuleService userRuleService;

    public CustomResponse<UserResponse> signUp(SignUpRequest request) {
        userRuleService.userEmailIsAlreadyExist(request.getEmail());

        User user = modelMapperService.forRequest().map(request, User.class);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);
        UserResponse userResponse = modelMapperService.forResponse().map(savedUser, UserResponse.class);
        return new CustomResponse<>(userResponse, "Kullanıcı Oluşturuldu.");
    }

    public CustomResponse<LoginResponse> signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
        ));

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserId(user.getId());
        loginResponse.setToken(jwt);
        loginResponse.setRefreshToken(refreshToken);
        return new CustomResponse<>(loginResponse, "Giriş Başarılı.");
    }

    public CustomResponse<LoginResponse> refreshToken(RefreshTokenRequest request) {
        String userEmail = jwtService.extractUserName(request.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(request.getToken(), user)) {
            var jwt = jwtService.generateToken(user);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(jwt);
            loginResponse.setRefreshToken(request.getToken());
            return new CustomResponse<>(loginResponse, "Token Yenileme işlemi Başarılı.");
        }
        return new CustomResponse<>(null, "Başarısız", false);
    }

}
