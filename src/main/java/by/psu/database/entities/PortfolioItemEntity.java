package by.psu.database.entities;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Table(name = "portfolio_item")
@Getter
@Setter
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class PortfolioItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "entity_key")
    private String id;

    @Column(name = "type")
    private String type;

    @Column(name = "order")
    private Integer order;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String metadata;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "portfolio_card_id", referencedColumnName = "id")
    private PortfolioCardEntity portfolioCard;
}
