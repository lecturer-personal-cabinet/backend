CREATE TABLE user_portfolio_card (
  id entity_key NOT NULL PRIMARY KEY DEFAULT generate_entity_key('UPC'),
  title VARCHAR(250) NOT NULL,
  description TEXT NOT NULL,
  preview_image_link VARCHAR(250) DEFAULT NULL,
  tags VARCHAR(20)[],
  user_id entity_key NOT NULL REFERENCES users(id)
);