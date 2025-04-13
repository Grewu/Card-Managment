INSERT INTO roles (id, name)
VALUES (1, 'ADMIN'),
       (2, 'USER'),
       (3, 'GUEST');

INSERT INTO privileges (id, name)
VALUES (1, 'READ'),
       (2, 'WRITE'),
       (3, 'DELETE');

INSERT INTO role_privilege (role_id, privilege_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (3, 1);


INSERT INTO users (id, email, password, role_id)
VALUES
    (1, 'user@example.com', '$2b$12$7K9Jj/SFX.un9yRyUDDMeel.fKmctqWf7SPVwOotCpFNn1pk4xCoO', 1),
    (2, 'john.doe@example.com', '$2b$12$7K9Jj/SFX.un9yRyUDDMeel.fKmctqWf7SPVwOotCpFNn1pk4xCoO', 2),
    (3, 'jane.smith@example.com', '$2b$12$7K9Jj/SFX.un9yRyUDDMeel.fKmctqWf7SPVwOotCpFNn1pk4xCoO', 3),
    (4, 'alice.brown@example.com', '$2b$12$7K9Jj/SFX.un9yRyUDDMeel.fKmctqWf7SPVwOotCpFNn1pk4xCoO', 2);
