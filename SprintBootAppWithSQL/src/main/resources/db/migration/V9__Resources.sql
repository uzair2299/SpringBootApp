CREATE TABLE Resources (
    id SERIAL PRIMARY KEY,
    resource_name VARCHAR(255) NOT NULL,
    resource_endpoint VARCHAR(255) NOT NULL,
    version VARCHAR(50) NOT NULL,
    is_active BOOLEAN NOT NULL,
    method_type VARCHAR(10) NOT NULL,
    description TEXT,
    is_auth_required BOOLEAN NOT NULL,
    rate_limit INTEGER,
    is_deprecated BOOLEAN NOT NULL,
    documentation_url VARCHAR(255),
    owner VARCHAR(255)
);


SELECT r.id,r.resource_name, p.id,p.permission_name FROM public.resources r
JOIN resources_permissions rp ON rp.resource_id =r.id
JOIN permission p ON rp.permission_id =p.id