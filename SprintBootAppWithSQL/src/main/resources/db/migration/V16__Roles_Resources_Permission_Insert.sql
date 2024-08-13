


CREATE TABLE Roles_Resources_Permissions (
 id BIGSERIAL PRIMARY KEY,
    resources_permissions_id INT,
    role_id INT,

    FOREIGN KEY (resources_permissions_id) REFERENCES resources_permissions(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

--INSERT INTO Roles_Resources_Permissions (resources_permissions_id, role_id)
--VALUES(71, 1)