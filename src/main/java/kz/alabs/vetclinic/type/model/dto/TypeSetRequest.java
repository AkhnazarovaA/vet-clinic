package kz.alabs.vetclinic.type.model.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeSetRequest {

    private Set<Long> ids;

}
