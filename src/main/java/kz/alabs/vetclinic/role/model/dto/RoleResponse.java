package kz.alabs.vetclinic.role.model.dto;

import kz.alabs.vetclinic.core.enums.RoleType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {

    private Long id;
    private RoleType type;

}
