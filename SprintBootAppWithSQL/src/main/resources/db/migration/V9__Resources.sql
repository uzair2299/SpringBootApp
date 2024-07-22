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
    'Example Resource 1',
    '/api/resource1',
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
    'Example Resource 2',
    '/api/resource2',
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