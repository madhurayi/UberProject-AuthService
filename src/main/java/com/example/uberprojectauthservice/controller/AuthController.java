package com.example.uberprojectauthservice.controller;

import com.example.uberprojectauthservice.dto.PassengerSignupRequestDto;
import com.example.uberprojectauthservice.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/signup/passenger")
    public ResponseEntity<?> signUp(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(passengerSignupRequestDto));
    }

    @GetMapping("/signin")
    public ResponseEntity<?> signIn(){
        return ResponseEntity.status(HttpStatus.OK).body(10);
    }
}
