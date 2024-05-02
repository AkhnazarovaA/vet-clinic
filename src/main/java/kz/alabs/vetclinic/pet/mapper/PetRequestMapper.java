package kz.alabs.vetclinic.pet.mapper;

import kz.alabs.vetclinic.pet.model.dto.PetRequest;
import kz.alabs.vetclinic.pet.model.entity.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PetRequestMapper {

    @Mapping(target = "name", expression = "java(org.apache.commons.text.WordUtils.capitalizeFully(dto.getName()))")
    Pet toEntity(PetRequest dto);

    PetRequest toDto(Pet entity);

}
