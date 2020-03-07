CREATE TABLE google_users (
    id VARCHAR(50) NOT NULL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    given_name VARCHAR(50) NOT NULL,
    family_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    image_url VARCHAR(80) NOT NULL,
    token VARCHAR(50) NOT NULL
);