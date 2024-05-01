package kz.alabs.vetclinic.role.model.entity;

import jakarta.persistence.*;
import kz.alabs.vetclinic.core.enums.RoleType;
import kz.alabs.vetclinic.user.model.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roles", schema = "public")
@NoArgsConstructor
@EqualsAndHashCode(of = {"type"})
public class Role {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq-role"
    )
    @SequenceGenerator(
            name = "seq-role",
            sequenceName = "seq_roles",
            allocationSize = 1
    )
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false, unique = true)
    private RoleType type;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role(Long id, RoleType type) {
        this.id = id;
        this.type = type;
    }

}
