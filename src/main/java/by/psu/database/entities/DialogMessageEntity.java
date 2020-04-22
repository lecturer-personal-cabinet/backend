package by.psu.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "dialog_message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DialogMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "entity_key")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dialog_id")
    private DialogEntity dialog;

    @Column(name = "content")
    private String content;

    @Column(name = "createdTs")
    private Timestamp createdTs;
}
