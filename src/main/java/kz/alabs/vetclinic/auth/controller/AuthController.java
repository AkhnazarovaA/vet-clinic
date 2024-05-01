package kz.alabs.vetclinic.auth.controller;

import jakarta.validation.Valid;
import kz.alabs.vetclinic.auth.model.dto.JwtResponse;
import kz.alabs.vetclinic.auth.model.dto.LoginRequest;
import kz.alabs.vetclinic.auth.service.AuthService;
import kz.alabs.vetclinic.user.model.dto.UserRequest;
import kz.alabs.vetclinic.user.model.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/signup")
    public UserResponse signup(@Valid @RequestBody UserRequest request) {
        return authService.signup(request);
    }

}
