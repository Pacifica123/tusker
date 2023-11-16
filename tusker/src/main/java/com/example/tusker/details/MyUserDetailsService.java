package com.example.tusker.details;

import com.example.tusker.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.SQLException;

public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Получаем информацию о пользователе из базы данных
        com.example.tusker.models.User u;
        try {
            u = userRepository.findByEmail(email);
        } catch (SQLException e) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }


        // Создаем объект UserDetails на основе информации о пользователе
        return User.builder()
                .username(u.email()) // В качестве username используем email
                .password(u.pass()) // Зашифрованный пароль
                .roles("USER") // Роли пользователя (может быть получено из базы данных в будущем)
                .build();
    }
}

