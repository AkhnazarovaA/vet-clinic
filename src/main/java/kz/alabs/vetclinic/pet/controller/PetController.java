package kz.alabs.vetclinic.pet.controller;

import jakarta.validation.Valid;
import kz.alabs.vetclinic.pet.model.dto.PetRequest;
import kz.alabs.vetclinic.pet.model.dto.PetResponse;
import kz.alabs.vetclinic.pet.service.PetService;
import kz.alabs.vetclinic.type.model.dto.TypeSetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole(T(kz.alabs.vetclinic.core.enums.RoleType).ROLE_USER)")
    public PetResponse create(@Valid @RequestBody PetRequest request) {
        return petService.create(request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole(T(kz.alabs.vetclinic.core.enums.RoleType).ROLE_USER)")
    public PetResponse findById(@PathVariable long id) {
        return petService.findById(id);
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasRole(T(kz.alabs.vetclinic.core.enums.RoleType).ROLE_USER)")
    public List<PetResponse> findAllByUserId(@PathVariable long userId) {
        return petService.findAllByUserId(userId);
    }

    @PostMapping("/types")
    @PreAuthorize("hasRole(T(kz.alabs.vetclinic.core.enums.RoleType).ROLE_USER)")
    public Map<String, Long> findAllByType(@Valid @RequestBody TypeSetRequest request) {
        return petService.findAllByType(request);
    }

    @GetMapping
    @PreAuthorize("hasRole(T(kz.alabs.vetclinic.core.enums.RoleType).ROLE_USER)")
    public Page<PetResponse> findAll(Pageable pageable) {
        return petService.findAll(pageable);
    }

    @PutMapping
    @PreAuthorize("hasRole(T(kz.alabs.vetclinic.core.enums.RoleType).ROLE_USER)")
    public PetResponse update(@Valid @RequestBody PetRequest request) {
       return petService.update(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole(T(kz.alabs.vetclinic.core.enums.RoleType).ROLE_USER)")
    public void deleteById(@PathVariable long id) {
        petService.deleteById(id);
    }

}
