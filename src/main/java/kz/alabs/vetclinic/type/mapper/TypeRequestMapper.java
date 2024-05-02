package kz.alabs.vetclinic.type.mapper;

import kz.alabs.vetclinic.type.model.dto.TypeRequest;
import kz.alabs.vetclinic.type.model.entity.Type;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TypeRequestMapper {

    @Mapping(target = "name", expression = "java(org.apache.commons.text.WordUtils.capitalizeFully(dto.getName()))")
    Type toEntity(TypeRequest dto);

    TypeRequest toDto(Type entity);

}
