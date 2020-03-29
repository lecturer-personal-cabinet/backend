CREATE TABLE user_timeline_post (
    id entity_key NOT NULL DEFAULT generate_entity_key('UTP'),
    title VARCHAR(100) NOT NULL,
    content TEXT,
    created_ts TIMESTAMP DEFAULT NOW(),
    sender_id entity_key NOT NULL REFERENCES users(id)
);