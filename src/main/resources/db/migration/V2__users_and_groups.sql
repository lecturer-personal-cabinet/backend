CREATE TYPE user_type AS enum ('LECTURER', 'FULL_TIME_STUDENT', 'UNKNOWN');

CREATE TABLE groups (
    id entity_key PRIMARY KEY  DEFAULT generate_entity_key('GRP'),
    title VARCHAR(50) NOT NULL
);

CREATE TABLE users (
    id entity_key PRIMARY KEY DEFAULT generate_entity_key('USR'),
    type user_type NOT NULL DEFAULT 'FULL_TIME_STUDENT',
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    patronymic VARCHAR(50),
    email VARCHAR(50) NOT NULL,
    group_id entity_key REFERENCES groups(id) DEFAULT NULL
);