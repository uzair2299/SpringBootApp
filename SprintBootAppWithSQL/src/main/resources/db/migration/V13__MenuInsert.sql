-- Level 1
INSERT INTO Menu (name, link, parent_id, status) VALUES ('Home', '/home', NULL, 1);
INSERT INTO Menu (name, link, parent_id, status) VALUES ('Profile', '/profile', NULL, 1);
INSERT INTO Menu (name, link, parent_id, status) VALUES ('Settings', '/settings', NULL, 1);
INSERT INTO Menu (name, link, parent_id, status) VALUES ('Help', '/help', NULL, 1);

-- Level 2
-- Home Submenus
INSERT INTO Menu (name, link, parent_id, status) VALUES ('Dashboard', '/home/dashboard', 1, 1);

-- Profile Submenus
INSERT INTO Menu (name, link, parent_id, status) VALUES ('View Profile', '/profile/view', 2, 1);
INSERT INTO Menu (name, link, parent_id, status) VALUES ('Edit Profile', '/profile/edit', 2, 1);

-- Settings Submenus
INSERT INTO Menu (name, link, parent_id, status) VALUES ('General Settings', '/settings/general', 3, 1);
INSERT INTO Menu (name, link, parent_id, status) VALUES ('Security Settings', '/settings/security', 3, 1);

-- Level 3
-- Security Settings Submenus
INSERT INTO Menu (name, link, parent_id, status) VALUES ('Change Password', '/settings/security/change-password', 8, 1);
INSERT INTO Menu (name, link, parent_id, status) VALUES ('Two-Factor Authentication', '/settings/security/2fa', 8, 1);
