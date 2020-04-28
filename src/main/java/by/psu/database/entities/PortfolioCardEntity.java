package by.psu.database.entities;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_portfolio_card")
@Getter
@Setter
@TypeDefs({
        @TypeDef(
                name = "string-array",
                typeClass = StringArrayType.class
        )
})
public class PortfolioCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "entity_key")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "preview_image_link")
    private String previewImageLink;

    @Column(name = "tags", columnDefinition = "varchar(20)[]")
    @Type( type = "string-array" )
    private String[] tags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
