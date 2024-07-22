CREATE TABLE Menu (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    link VARCHAR(255) NOT NULL,
    parent_id BIGINT,
    status INT NOT NULL,
    CONSTRAINT fk_parent_menu_id FOREIGN KEY (parent_id) REFERENCES Menu(id)
);