

ALTER TABLE Roles_Resources_Permissions
ADD COLUMN user_id BIGINT;



UPDATE Roles_Resources_Permissions
SET user_id = 1;

ALTER TABLE Roles_Resources_Permissions
ADD CONSTRAINT fk_user_id
FOREIGN KEY (user_id) REFERENCES app_user(id);

