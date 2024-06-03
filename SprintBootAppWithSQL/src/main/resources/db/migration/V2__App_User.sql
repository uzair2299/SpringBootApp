CREATE TABLE app_user (
    id BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    date_joined TIMESTAMP NOT NULL,
    last_login TIMESTAMP,
    is_active BOOLEAN NOT NULL,
    is_locked BOOLEAN NOT NULL,
    profile_picture VARCHAR(255),
    bio TEXT,
    primary_phone VARCHAR(15),
    secondary_phone VARCHAR(15),
    work_phone VARCHAR(15)
);
