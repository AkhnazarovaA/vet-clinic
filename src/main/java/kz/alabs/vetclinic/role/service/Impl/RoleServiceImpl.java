package kz.alabs.vetclinic.role.service.Impl;

import kz.alabs.vetclinic.role.model.entity.Role;
import kz.alabs.vetclinic.role.repository.RoleRepository;
import kz.alabs.vetclinic.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

}
