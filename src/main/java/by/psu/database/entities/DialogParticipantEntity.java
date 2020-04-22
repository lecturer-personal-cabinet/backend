package by.psu.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "dialog_participant")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DialogParticipantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "entity_key")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dialog_id")
    private DialogEntity dialog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UserEntity user;
}
