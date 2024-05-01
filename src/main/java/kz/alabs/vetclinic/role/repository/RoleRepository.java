package kz.alabs.vetclinic.role.repository;

import kz.alabs.vetclinic.role.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
