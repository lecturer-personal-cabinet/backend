CREATE TABLE portfolio_item (
    id entity_key NOT NULL PRIMARY KEY DEFAULT generate_entity_key('PI'),
    portfolio_card_id entity_key NOT NULL REFERENCES user_portfolio_card(id),
    type VARCHAR(20) NOT NULL,
    "order" INT NOT NULL,
    metadata jsonb NOT NULL
)