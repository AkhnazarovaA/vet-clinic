package kz.alabs.vetclinic.type.mapper;

import kz.alabs.vetclinic.type.model.dto.TypeResponse;
import kz.alabs.vetclinic.type.model.entity.Type;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeResponseMapper {

    Type toEntity(TypeResponse dto);

    TypeResponse toDto(Type entity);

}
