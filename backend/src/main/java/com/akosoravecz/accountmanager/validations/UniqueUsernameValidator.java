package com.akosoravecz.accountmanager.validations;

import com.akosoravecz.accountmanager.repository.user.UserRepository;
import com.akosoravecz.accountmanager.validations.annotations.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username == null) return true;
        return userRepository.findByUsername(username).isEmpty();
    }
}
