package kz.alabs.vetclinic.pet.model.entity;

import jakarta.persistence.*;
import kz.alabs.vetclinic.type.model.entity.Type;
import kz.alabs.vetclinic.user.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "pets", schema = "public")
public class Pet {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq-pet"
    )
    @SequenceGenerator(
            name = "seq-pet",
            sequenceName = "seq_pets",
            allocationSize = 1
    )
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return getId() != null &&
                Objects.equals(getId(), pet.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
