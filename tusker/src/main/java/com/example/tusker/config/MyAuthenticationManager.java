package com.example.tusker.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

public class MyAuthenticationManager implements AuthenticationManager {
    private final AuthenticationManager authenticationManager;
    public MyAuthenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        AuthenticationProvider authenticationProvider = new MyAuthenticationProvider(userDetailsService, passwordEncoder);
        this.authenticationManager = new ProviderManager(Collections.singletonList(authenticationProvider));
    }
    @Override
    public Authentication authenticate(Authentication authentication) {
        return authenticationManager.authenticate(authentication);
    }
}
