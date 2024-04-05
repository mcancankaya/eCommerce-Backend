package com.mcancankaya.ecommerce.services;

import com.mcancankaya.ecommerce.core.mapper.ModelMapperService;
import com.mcancankaya.ecommerce.entities.User;
import com.mcancankaya.ecommerce.repositories.UserRepository;
import com.mcancankaya.ecommerce.services.dtos.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapperService modelMapperService;

    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı. " + username));
    }

    public UserResponse getByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı."));

        UserResponse userResponse = modelMapperService.forResponse().map(user, UserResponse.class);

        return userResponse;

    }

    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();

        List<UserResponse> userResponses = users.stream().map(user -> modelMapperService.forResponse().map(user, UserResponse.class)).toList();
        return userResponses;
    }

    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Kullanıcı Bulunamadı."));
    }
}
