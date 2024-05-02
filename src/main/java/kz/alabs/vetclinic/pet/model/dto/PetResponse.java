package kz.alabs.vetclinic.pet.model.dto;

import kz.alabs.vetclinic.type.model.dto.TypeResponse;
import kz.alabs.vetclinic.user.model.dto.UserResponse;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetResponse {

    private Long id;
    private String name;
    private TypeResponse type;
    private UserResponse user;

}
