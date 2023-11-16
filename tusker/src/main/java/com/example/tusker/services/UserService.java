package com.example.tusker.services;

import com.example.tusker.dto.UserRegistrationDto;
import com.example.tusker.models.User;
import com.example.tusker.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void registerUser(UserRegistrationDto registrationDto) {
        // Ваша логика регистрации, например, сохранение пользователя в базу данных
        String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());
        userRepository.create(
                new User.Builder()
                        .Email(registrationDto.getEmail())
                        .Name(registrationDto.getUsername())
                        .Password(encodedPassword)
                        .build()
        );
    }
}
