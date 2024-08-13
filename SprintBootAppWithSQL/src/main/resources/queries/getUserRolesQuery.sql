SELECT u.id, u.user_name, u.first_name,u.last_name,u.email, r.id AS role_id,r.role_name FROM app_user AS u
JOIN user_roles ur ON ur.user_id=u.id
JOIN roles r ON r.id =ur.role_id
WHERE u.id =1