package kz.alabs.vetclinic.pet.mapper;

import kz.alabs.vetclinic.pet.model.dto.PetResponse;
import kz.alabs.vetclinic.pet.model.entity.Pet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PetResponseMapper {

    Pet toEntity(PetResponse dto);

    PetResponse toDto(Pet entity);

}
