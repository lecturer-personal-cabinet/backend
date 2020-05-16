package by.psu.database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dialog")
@Setter
@Getter
public class DialogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "entity_key")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "participants_hash")
    private String participantsHash;

    @OneToMany(mappedBy="dialog")
    private Set<DialogMessageEntity> messages;
}
