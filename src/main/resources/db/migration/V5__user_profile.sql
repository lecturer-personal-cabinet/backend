CREATE TABLE user_profile (
  id entity_key NOT NULL DEFAULT generate_entity_key('UP'),
  user_id entity_key NOT NULL REFERENCES users(id) UNIQUE,
  description TEXT DEFAULT '',
  timezone VARCHAR(30) DEFAULT NULL,
  address VARCHAR(50) DEFAULT NULL,
  phone_number VARCHAR(50) DEFAULT NULL
);