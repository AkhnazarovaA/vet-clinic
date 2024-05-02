package kz.alabs.vetclinic.auth.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class JwtResponse {

    private String token;
    private Long id;
    private String username;
    private List<String> roles;

}
