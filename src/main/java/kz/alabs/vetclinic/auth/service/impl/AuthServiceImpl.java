package kz.alabs.vetclinic.auth.service.impl;

import kz.alabs.vetclinic.auth.model.dto.JwtResponse;
import kz.alabs.vetclinic.auth.model.dto.LoginRequest;
import kz.alabs.vetclinic.auth.service.AuthService;
import kz.alabs.vetclinic.core.enums.RoleType;
import kz.alabs.vetclinic.core.exception.ElementAlreadyExistsException;
import kz.alabs.vetclinic.core.security.JwtUtils;
import kz.alabs.vetclinic.core.security.UserDetailsImpl;
import kz.alabs.vetclinic.role.model.entity.Role;
import kz.alabs.vetclinic.user.mapper.UserRequestMapper;
import kz.alabs.vetclinic.user.mapper.UserResponseMapper;
import kz.alabs.vetclinic.user.model.dto.UserRequest;
import kz.alabs.vetclinic.user.model.dto.UserResponse;
import kz.alabs.vetclinic.user.model.entity.User;
import kz.alabs.vetclinic.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

import static kz.alabs.vetclinic.core.util.Constants.ALREADY_EXISTS_USER;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @Override
    @Transactional
    public JwtResponse login(LoginRequest request) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername().trim(), request.getPassword().trim()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        final List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return JwtResponse.builder().token(jwt).id(userDetails.getId()).username(userDetails.getUsername()).roles(roles).build();
    }

    @Override
    @Transactional
    public UserResponse signup(UserRequest request) {
        if (userRepository.existsByUsernameIgnoreCase(request.getUsername().trim())) {
            throw new ElementAlreadyExistsException(ALREADY_EXISTS_USER);
        }
        final User user = userRequestMapper.toEntity(request);
        user.setPassword(encoder.encode(request.getPassword().trim()));
        user.setRoles(new HashSet<>(List.of(new Role(1L, RoleType.ROLE_USER))));
        userRepository.save(user);
        log.info("User has been created");
        return userResponseMapper.toDto(user);
    }

}
