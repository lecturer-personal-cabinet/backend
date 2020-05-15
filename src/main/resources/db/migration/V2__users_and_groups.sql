CREATE TABLE groups (
    id entity_key PRIMARY KEY  DEFAULT generate_entity_key('GRP'),
    title VARCHAR(50) NOT NULL
);

CREATE TABLE users (
    id entity_key PRIMARY KEY DEFAULT generate_entity_key('USR'),
    type VARCHAR(50) NOT NULL DEFAULT 'USER',
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    patronymic VARCHAR(50),
    email VARCHAR(50) NOT NULL,
    password VARCHAR(60) NOT NULL,
    image VARCHAR(300) DEFAULT NULL,
    group_id entity_key REFERENCES groups(id) DEFAULT NULL
);