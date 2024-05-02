package kz.alabs.vetclinic.type.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeResponse {

    private Long id;
    private String name;
    private String description;

}
