CREATE TABLE classroom_codes (
  id entity_key PRIMARY KEY DEFAULT generate_entity_key('CLC'),
  title VARCHAR(100) NOT NULL,
  classroom_code VARCHAR(8) NOT NULL,
  owner_id VARCHAR(50) NOT NULL REFERENCES users (id)
);

CREATE TABLE classroom_code_assignment (
  id entity_key NOT NULL DEFAULT generate_entity_key('CCA'),
  classroom_code_id entity_key NOT NULL REFERENCES classroom_codes(id),
  group_id entity_key NOT NULL REFERENCES groups(id),
  UNIQUE (classroom_code_id, group_id)
);