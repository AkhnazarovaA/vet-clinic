package kz.alabs.vetclinic.user.model.dto;

import kz.alabs.vetclinic.role.model.dto.RoleResponse;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String fullName;
    private Set<RoleResponse> roles;

}
