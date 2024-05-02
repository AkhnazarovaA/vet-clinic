package kz.alabs.vetclinic.pet.service;

import kz.alabs.vetclinic.pet.model.dto.PetRequest;
import kz.alabs.vetclinic.pet.model.dto.PetResponse;
import kz.alabs.vetclinic.type.model.dto.TypeSetRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PetService {

    PetResponse create(PetRequest request);

    Page<PetResponse> findAll(Pageable pageable);

    Map<String, Long> findAllByType(TypeSetRequest types);

    List<PetResponse> findAllByUserId(long userId);

    PetResponse findById(long id);

    PetResponse update(PetRequest request);

    void deleteById(long id);

}
