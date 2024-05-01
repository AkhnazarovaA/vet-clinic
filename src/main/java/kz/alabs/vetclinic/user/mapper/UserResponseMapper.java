package kz.alabs.vetclinic.user.mapper;

import kz.alabs.vetclinic.user.model.dto.UserResponse;
import kz.alabs.vetclinic.user.model.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    User toEntity(UserResponse dto);

    UserResponse toDto(User entity);

    @AfterMapping
    default void setFullName(@MappingTarget UserResponse dto, User entity) {
        dto.setFullName("%s %s".formatted(entity.getFirstName(), entity.getLastName()).trim());
    }

}
