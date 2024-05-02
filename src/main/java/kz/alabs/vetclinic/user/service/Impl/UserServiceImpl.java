package kz.alabs.vetclinic.user.service.Impl;

import kz.alabs.vetclinic.core.enums.RoleType;
import kz.alabs.vetclinic.core.exception.ElementAlreadyExistsException;
import kz.alabs.vetclinic.core.exception.NoSuchElementFoundException;
import kz.alabs.vetclinic.role.model.entity.Role;
import kz.alabs.vetclinic.user.mapper.UserRequestMapper;
import kz.alabs.vetclinic.user.mapper.UserResponseMapper;
import kz.alabs.vetclinic.user.model.dto.ProfileRequest;
import kz.alabs.vetclinic.user.model.dto.UserRequest;
import kz.alabs.vetclinic.user.model.dto.UserResponse;
import kz.alabs.vetclinic.user.model.entity.User;
import kz.alabs.vetclinic.user.repository.UserRepository;
import kz.alabs.vetclinic.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.WordUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static kz.alabs.vetclinic.core.util.Constants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;

    @Override
    @Transactional
    public UserResponse create(UserRequest request) {
        if (userRepository.existsByUsernameIgnoreCase(request.getUsername())) {
            throw new ElementAlreadyExistsException(ALREADY_EXISTS_USER);
        }
        final Set<Role> roles = new HashSet<>(List.of(new Role(1L, RoleType.ROLE_USER)));

        if (
                request.getRoles() != null
                        && request.getRoles().contains(RoleType.ROLE_ADMIN.name())
        ) {
            roles.add(new Role(2L, RoleType.ROLE_ADMIN));
        }

        final User user = userRequestMapper.toEntity(request);
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRoles(roles);
        userRepository.save(user);
        log.info("User has been created");
        return userResponseMapper.toDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> findAll(Pageable pageable) {
        final Page<UserResponse> users = userRepository.findAll(pageable)
                .map(userResponseMapper::toDto);

        if (users.isEmpty()) {
            throw new NoSuchElementFoundException(NOT_FOUND_RECORD);
        }
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(long id) {
        return userRepository.findById(id)
                .map(userResponseMapper::toDto)
                .orElseThrow(() -> new NoSuchElementFoundException(NOT_FOUND_USER));
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException(NOT_FOUND_USER));
    }

    @Override
    @Transactional
    public UserResponse update(ProfileRequest request) {
        final User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new NoSuchElementFoundException(NOT_FOUND_USER));

        if (
            request.getRoles() != null
            && request.getRoles().contains(RoleType.ROLE_ADMIN.name())
        ) {
            user.addRole(new Role(2L, RoleType.ROLE_ADMIN));
        } else {
            user.removeRole(new Role(2L, RoleType.ROLE_ADMIN));
        }

        user.setFirstName(WordUtils.capitalizeFully(request.getFirstName()));
        user.setLastName(WordUtils.capitalizeFully(request.getLastName()));
        userRepository.save(user);
        log.info("User has been updated");
        return userResponseMapper.toDto(user);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException(NOT_FOUND_USER));
        userRepository.delete(user);
        log.info("User has been deleted");
    }

}
