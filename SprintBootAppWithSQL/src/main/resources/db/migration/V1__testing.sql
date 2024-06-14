CREATE TABLE permission (
    id BIGSERIAL PRIMARY KEY,
    permission_name VARCHAR(255) NOT NULL,
    code VARCHAR(100) UNIQUE,
    module VARCHAR(255),
    created_at BIGINT,
    updated_at BIGINT,
    is_deleted BOOLEAN,
    description TEXT
);

