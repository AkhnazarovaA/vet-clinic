package kz.alabs.vetclinic.type.controller;

import jakarta.validation.Valid;
import kz.alabs.vetclinic.type.model.dto.TypeRequest;
import kz.alabs.vetclinic.type.model.dto.TypeResponse;
import kz.alabs.vetclinic.type.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/types")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole(T(kz.alabs.vetclinic.core.enums.RoleType).ROLE_USER)")
    public TypeResponse create(@Valid @RequestBody TypeRequest request) {
        return typeService.create(request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole(T(kz.alabs.vetclinic.core.enums.RoleType).ROLE_USER)")
    public TypeResponse findById(@PathVariable long id) {
        return typeService.findById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole(T(kz.alabs.vetclinic.core.enums.RoleType).ROLE_USER)")
    public Page<TypeResponse> findAll(Pageable pageable) {
        return typeService.findAll(pageable);
    }

    @PutMapping
    @PreAuthorize("hasRole(T(kz.alabs.vetclinic.core.enums.RoleType).ROLE_USER)")
    public TypeResponse update(@Valid @RequestBody TypeRequest request) {
        return typeService.update(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole(T(kz.alabs.vetclinic.core.enums.RoleType).ROLE_USER)")
    public void deleteById(@PathVariable long id) {
        typeService.deleteById(id);
    }

}
