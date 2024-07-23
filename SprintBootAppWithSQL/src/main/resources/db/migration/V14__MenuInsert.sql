
-- Level 1
INSERT INTO Menu (name, icon, link, parent_id, status) VALUES ('Notification', 'notification_important', '/profile', NULL, 1);



-- Profile Submenus
INSERT INTO Menu (name, link, icon, parent_id, status) VALUES ('Settings', 'settings','/profile/view', 1, 1);
INSERT INTO Menu (name, link, icon, parent_id, status) VALUES ('Send Us Feedback', '/settings','feedback', 1, 1);