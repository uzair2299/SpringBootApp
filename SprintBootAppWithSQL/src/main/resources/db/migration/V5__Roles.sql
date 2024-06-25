CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    role_Name VARCHAR(255) NOT NULL UNIQUE,
    created_at BIGINT,
    updated_at BIGINT,
    is_deleted BOOLEAN,
    description TEXT
);
