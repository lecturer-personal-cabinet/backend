package by.psu.database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "dialog_message")
@Setter
@Getter
public class DialogMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "entity_key")
    private String id;

    @Column(name = "content")
    private String content;

    @Column(name = "createdTs")
    private Timestamp createdTs;

    @ManyToOne
    @JoinColumn(name="dialog_id")
    private DialogEntity dialog;

    @ManyToOne
    @JoinColumn(name="sender_id")
    private UserEntity sender;

    @Column(name = "is_read")
    private Boolean isRead;

}
