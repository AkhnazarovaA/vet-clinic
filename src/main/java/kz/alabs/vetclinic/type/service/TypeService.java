package kz.alabs.vetclinic.type.service;

import kz.alabs.vetclinic.type.model.dto.TypeRequest;
import kz.alabs.vetclinic.type.model.dto.TypeResponse;
import kz.alabs.vetclinic.type.model.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeService {

    TypeResponse create(TypeRequest request);

    TypeResponse findById(long id);

    Type getById(long id);

    Page<TypeResponse> findAll(Pageable pageable);

    TypeResponse update(TypeRequest request);

    void deleteById(long id);

}
