


CREATE TABLE Resources_Permissions (
 id BIGSERIAL PRIMARY KEY,
    permission_id INT,
    resource_id INT,

    FOREIGN KEY (permission_id) REFERENCES permission(id),
    FOREIGN KEY (resource_id) REFERENCES resources(id)
);

