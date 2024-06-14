INSERT INTO permission (permission_name, code, module, created_at, updated_at, is_deleted, description)
VALUES
('VIEW', 'VIEW', 'General', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, TRUE, 'Permission to view/read data or content.'),
('LIST', 'LIST', 'General', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, TRUE, 'Permission to list multiple items or records.'),
('SEARCH', 'SEARCH', 'General', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, TRUE, 'Permission to search for specific items or records.'),
('EXPORT', 'EXPORT', 'General', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, TRUE, 'Permission to export data or content.'),
('CREATE', 'CREATE', 'General', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, TRUE, 'Permission to create new items or records.'),
('EDIT', 'EDIT', 'General', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, TRUE, 'Permission to modify existing items or records.'),
('UPDATE', 'UPDATE', 'General', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, FALSE, 'Permission to update specific fields or properties of items or records.'),
('MODIFY', 'MODIFY', 'General', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, FALSE, 'General permission to make changes (can include both edit and update).'),
('DELETE', 'DELETE', 'General', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, FALSE, 'Permission to delete items or records.'),
('REMOVE', 'REMOVE', 'General', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, FALSE, 'Permission to remove items or records (may imply a soft delete or archive action).'),
('ADMINISTER', 'ADMINISTER', 'General', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, FALSE, 'Permission to perform administrative tasks or manage system settings.'),
('MANAGE', 'MANAGE', 'General', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, FALSE, 'General permission to manage resources, users, or configurations.'),
('ACCESS', 'ACCESS', 'General', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, FALSE, 'Permission to access a specific feature, module, or area of the application.'),
('AUTHORIZE', 'AUTHORIZE', 'General', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, FALSE, 'Permission to authorize or grant access to other users.'),
('EXECUTE', 'EXECUTE', 'General', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, FALSE, 'Permission to execute or run specific operations or commands.'),
('PERFORM', 'PERFORM', 'General', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, FALSE, 'General permission to perform actions (can include various operations).'),
('CUSTOM', 'CUSTOM', 'General', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, FALSE, 'Custom-defined permissions tailored to specific application requirements.');