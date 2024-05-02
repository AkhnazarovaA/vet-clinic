package kz.alabs.vetclinic.pet.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetRequest {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    private Long typeId;

    @NotNull
    private Long userId;

}