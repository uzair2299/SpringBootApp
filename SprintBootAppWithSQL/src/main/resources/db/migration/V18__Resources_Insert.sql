
-- Inserting sample data into your_table_name
INSERT INTO Resources (
    resource_name,
    resource_endpoint,
    version,
    is_active,
    method_type,
    description,
    is_auth_required,
    rate_limit,
    is_deprecated,
    documentation_url,
    owner
) VALUES
(
    'Get Resources with permission',
    '/api/v1/resources/getAllResourcesWithPermissions',
    'v1',
    true,
    'GET',
    'This is an example resource 1',
    true,
    100,
    false,
    'https://example.com/docs/resource1',
    'John Doe'
),
(
    'Add new Resource',
    '/api/v1/resources/CreateNewPermission',
    'v2',
    true,
    'POST',
    'This is an example resource 2',
    false,
    NULL,
    true,
    NULL,
    'Jane Smith'
);