package com.eis.transportetotal.services;


import com.eis.transportetotal.dtos.LoginUserDto;
import com.eis.transportetotal.dtos.RegisterUserDto;
import com.eis.transportetotal.entity.User;
import com.eis.transportetotal.repositories.IUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AuthenticationService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            IUserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        var user = new User();
        Optional<User> existUser = userRepository.findByEmail(input.getEmail());
        if (existUser.isPresent()){
            return existUser.get();
        }
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setName(input.getName());
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           input.getEmail(),
                           input.getPassword()
                   )
           );

           return userRepository.findByEmail(input.getEmail()).orElseThrow();
       }
       catch (BadCredentialsException e) {
           throw new BadCredentialsException(e.getMessage());
       }
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}

