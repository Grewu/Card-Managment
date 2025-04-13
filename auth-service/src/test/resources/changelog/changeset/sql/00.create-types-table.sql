CREATE TYPE user_role AS ENUM (
    'ADMIN',
    'USER',
    'GUEST'
    );

CREATE TYPE privileges_type AS ENUM (
    'READ',
    'WRITE',
    'DELETE'
    );
