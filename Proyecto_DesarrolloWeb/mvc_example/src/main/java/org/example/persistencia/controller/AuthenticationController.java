package org.example.persistencia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.example.persistencia.dto.JwtAuthenticationResponse;
import org.example.persistencia.dto.LoginDTO;
import org.example.persistencia.dto.UserRegistrationDTO;
import org.example.persistencia.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody UserRegistrationDTO request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginDTO request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

}