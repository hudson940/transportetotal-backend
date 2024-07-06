package com.eis.transportetotal.controllers;

import com.eis.transportetotal.dtos.LoginResponse;
import com.eis.transportetotal.dtos.LoginUserDto;
import com.eis.transportetotal.dtos.RegisterUserDto;
import com.eis.transportetotal.entity.User;
import com.eis.transportetotal.services.AuthenticationService;
import com.eis.transportetotal.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody LoginUserDto loginUserDto) {
        try {
            User authenticatedUser = authenticationService.authenticate(loginUserDto);

            String jwtToken = jwtService.generateToken(authenticatedUser);

            LoginResponse loginResponse = new LoginResponse()
                                              .setToken(jwtToken)
                                              .setExpiresIn(jwtService.getExpirationTime())
                                              .setUserName(authenticatedUser.getUsername());

            return ResponseEntity.ok(loginResponse);
        }
        catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


}

