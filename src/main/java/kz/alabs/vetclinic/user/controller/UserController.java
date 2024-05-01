package kz.alabs.vetclinic.user.controller;

import jakarta.validation.Valid;
import kz.alabs.vetclinic.user.model.dto.ProfileRequest;
import kz.alabs.vetclinic.user.model.dto.UserRequest;
import kz.alabs.vetclinic.user.model.dto.UserResponse;
import kz.alabs.vetclinic.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole(T(kz.alabs.vetclinic.core.enums.RoleType).ROLE_ADMIN)")
    public UserResponse create(@Valid @RequestBody UserRequest request) {
        return userService.create(request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole(T(kz.alabs.vetclinic.core.enums.RoleType).ROLE_USER)")
    public UserResponse findById(@PathVariable long id) {
        return userService.findById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole(T(kz.alabs.vetclinic.core.enums.RoleType).ROLE_ADMIN)")
    public Page<UserResponse> findAll(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @PutMapping
    @PreAuthorize("hasRole(T(kz.alabs.vetclinic.core.enums.RoleType).ROLE_USER)")
    public UserResponse update(@Valid @RequestBody ProfileRequest request) {
        return userService.update(request);
    }

    @PutMapping("/profile")
    @PreAuthorize("hasRole(T(kz.alabs.vetclinic.core.enums.RoleType).ROLE_USER)")
    public UserResponse updateFullName(@Valid @RequestBody ProfileRequest request) {
        return userService.updateFullName(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole(T(kz.alabs.vetclinic.core.enums.RoleType).ROLE_ADMIN)")
    public void deleteById(@PathVariable long id) {
        userService.deleteById(id);
    }

}