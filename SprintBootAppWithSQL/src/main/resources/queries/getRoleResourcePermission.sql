
SELECT r.id AS r_role_id,
r.role_name,
rrp.role_id AS rrp_role_id,
rrp.resources_permissions_id AS rrp_resources_permissions_id,
rp.id AS rp_id,
rp.resource_id AS rp_resource_id,
rp.permission_id AS rp_permission_id,
p.permission_name,
p.id AS p_id,
re.id AS re_id,
re.resource_name,
re.method_type,
re.resource_endpoint

FROM roles r
JOIN roles_resources_permissions rrp ON r.id = rrp.role_id
JOIN resources_permissions rp ON rp.id = rrp.resources_permissions_id
JOIN public.permission  p ON p.id =rp.permission_id
JOIN resources re ON r.id = rp.resource_id
WHERE r.id =1 AND re.resource_endpoint = '/api/resource1'