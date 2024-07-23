CREATE TABLE Menu (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    icon VARCHAR(255) NOT NULL,
    link VARCHAR(255) NOT NULL,
    parent_id BIGINT,
    status INT NOT NULL,
    CONSTRAINT fk_parent_menu_id FOREIGN KEY (parent_id) REFERENCES Menu(id)
);

-- Level 1
INSERT INTO Menu (name, icon, link, parent_id, status) VALUES ('Profile', 'account_circle', '/profile', NULL, 1);



-- Profile Submenus
INSERT INTO Menu (name, link, icon, parent_id, status) VALUES ('Sign Out', 'exit_to_app','/profile/view', 1, 1);