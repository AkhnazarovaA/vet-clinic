package kz.alabs.vetclinic.auth.service;

import kz.alabs.vetclinic.auth.model.dto.JwtResponse;
import kz.alabs.vetclinic.auth.model.dto.LoginRequest;
import kz.alabs.vetclinic.user.model.dto.UserRequest;
import kz.alabs.vetclinic.user.model.dto.UserResponse;

public interface AuthService {

    JwtResponse login(LoginRequest request);

    UserResponse signup(UserRequest request);

}
