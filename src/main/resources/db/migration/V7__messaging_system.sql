CREATE TABLE dialog (
    id entity_key NOT NULL PRIMARY KEY DEFAULT generate_entity_key('DG'),
    name VARCHAR(100) DEFAULT NULL
);

CREATE TABLE dialog_participant (
    id entity_key NOT NULL PRIMARY KEY DEFAULT generate_entity_key('DP'),
    dialog_id entity_key NOT NULL REFERENCES dialog(id),
    user_id entity_key NOT NULL REFERENCES users(id),
    UNIQUE (dialog_id, user_id)
);

CREATE TABLE dialog_message (
  id entity_key NOT NULL PRIMARY KEY DEFAULT generate_entity_key('MSG'),
  dialog_id entity_key NOT NULL REFERENCES dialog(id),
  created_ts timestamp NOT NULL DEFAULT NOW(),
  content TEXT NOT NULL
);