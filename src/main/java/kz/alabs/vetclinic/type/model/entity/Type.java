package kz.alabs.vetclinic.type.model.entity;

import jakarta.persistence.*;
import kz.alabs.vetclinic.pet.model.entity.Pet;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "types", schema = "public")
@EqualsAndHashCode(of = {"name"})
public class Type {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq-type"
    )
    @SequenceGenerator(
            name = "seq-type",
            sequenceName = "seq_types",
            allocationSize = 1
    )
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(length = 50)
    private String description;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private Set<Pet> pets = new HashSet<>();

}
