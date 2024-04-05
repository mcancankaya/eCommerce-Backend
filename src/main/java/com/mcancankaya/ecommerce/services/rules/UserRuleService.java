package com.mcancankaya.ecommerce.services.rules;

import com.mcancankaya.ecommerce.core.exceptions.BusinessException;
import com.mcancankaya.ecommerce.entities.User;
import com.mcancankaya.ecommerce.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRuleService {
    private final UserRepository userRepository;

    public void userEmailIsAlreadyExist(String userEmail) {
        Optional<User> user = userRepository.findByEmail(userEmail);
        if (user.isPresent()) {
            throw new BusinessException("User email already exists.");
        }
    }
}
