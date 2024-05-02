package kz.alabs.vetclinic.type.reposity;

import kz.alabs.vetclinic.type.model.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    boolean existsByNameIgnoreCase(String name);

}
