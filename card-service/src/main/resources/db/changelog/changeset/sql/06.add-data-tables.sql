INSERT INTO cards (encrypted_number, user_email, expiry_date, status, balance)
VALUES
    ('7ac891f3c2f3515d908efc150844fc45e1c2c7f8f72fb8074c57ecd994f391109d6b7fc00af51cb0bc4523769e66d66f', 'user1@example.com', '2025-12-31 23:59:59', 'ACTIVE', 1000.00),
    ('1bd808ed42ac0f4813ba1f1f9f736322c293b790089929d24995a60215ccaa00e829336262e23e4724dba03a62b274be', 'user2@example.com', '2024-06-30 23:59:59', 'ACTIVE', 500.50),
    ('d01613bb87354153df922be342b065dc92ea1bac52946c0d91e5688b3f701f165c7fdf6243b1c19a60a80984346bfc77', 'user3@example.com', '2026-03-15 23:59:59', 'ACTIVE', NULL),
    ('8f389e383bd94a62f06d2c0b33f64b2458a3b9d92f935211fc531ff06bb3d7d35830ec4b5722c7e8030ff6011a02cbae', 'user1@example.com', '2025-10-31 23:59:59', 'BLOCKED', 0.00),
    ('0dfc97564e4ca4d61b5c6f199f2c69111e2b7f9880d7a8bc96913c3b51d2aced7c9d9799f453176184d4cf325116bda6', 'user4@example.com', '2024-09-30 23:59:59', 'BLOCKED', NULL),
    ('ff120b520424aed006d4be09f9cc69a33e5534973ba0fa2909193b6fa4194e11f3f329ca4241791c1cdc6604ad9df3b3', 'user5@example.com', '2022-12-31 23:59:59', 'EXPIRED', 10.25),
    ('7322167e27b8ae11b526b6ea1e6cf0581b8c7cb8822e12b2ba3b14d8e2650b81470ca11191386a37bed6d39ca2190baa', 'user3@example.com', '2023-01-15 23:59:59', 'EXPIRED', NULL),
    ('2dba2e0ff4afb549d35ea833bfd3d641d3dcf2cec2d827963de153362ae62e1eff129cbe38b3573997643ae39ec9ff72', 'vip@example.com', '2027-12-31 23:59:59', 'ACTIVE', 100000.99),
    ('5ab0a3348b7daf1c9c83c4576576d747ebfe8de5a8722c51e1f824fd02bde923d35f7236381cb72ed868db3310e398f2', 'premium@example.com', '2026-06-30 23:59:59', 'ACTIVE', 50000.00),
    ('8fadd700343d5557724844bc54568bc42600eac00f942919e99ea756e09858d4db609698ab3a6fdde74365a9ecf6b748', 'pavelgurevichwork@gmail.com', '2025-01-01 23:59:59', 'ACTIVE', NULL);




INSERT INTO transactions (id, amount, created_at, source_card_id, target_card_id, type)
VALUES
    ('a8b9d2f4-19c5-4b4e-94f0-1e79bfc826e0', 200.00, '2025-04-01 09:00:00', 1, 2, 'TRANSFER'),
    ('1f07b93e-49a6-4c88-8f71-637d7ef69df9', 100.00, '2025-04-02 10:30:00', 1, NULL, 'WITHDRAWAL'),
    ('f7a4e1b3-8df4-4e89-bc3d-3f74198c18c4', 300.00, '2025-04-03 11:15:00', 2, 5, 'TRANSFER'),
    ('b45c9ec0-bbfa-4875-b4aa-8e25dba4a3e0', 500.00, '2025-04-04 13:45:00', 3, NULL, 'WITHDRAWAL'),
    ('3ea45f69-0c3c-4cd3-aeb5-4f174d4a6b99', 450.00, '2025-04-05 14:10:00', 5, 6, 'TRANSFER'),
    ('d2a19e08-4fcb-44e7-9f33-53b327e4e334', 250.00, '2025-04-06 16:20:00', 6, NULL, 'DEPOSIT'),
    ('22db0f3d-bf9d-476c-8ff5-d5fdc8f1a61f', 600.00, '2025-04-07 17:30:00', 8, 1, 'TRANSFER'),
    ('f6dc4cd9-d172-4e92-9f29-3c387f7b2011', 150.00, '2025-04-08 08:45:00', 7, NULL, 'WITHDRAWAL'),
    ('c24b2cf1-06e4-4a90-aeb5-69ce62fa2239', 90.00, '2025-04-09 10:15:00', 2, NULL, 'DEPOSIT'),
    ('34af2e8f-9a87-44cd-9881-fc08879bc118', 700.00, '2025-04-10 12:00:00', 4, 3, 'TRANSFER');



-- Insert card limits (1:1 relationship with cards table)
INSERT INTO card_limits (card_id, daily_limit, monthly_limit, updated_at)
VALUES
    -- Card 1: Standard active card with moderate limits
    (1, 1000.00, 30000.00, '2025-04-01 09:00:00'),

    -- Card 2: Standard active card with lower limits
    (2, 500.00, 15000.00, '2025-04-02 10:30:00'),

    -- Card 3: Active card with higher than standard limits
    (3, 2000.00, 60000.00, '2025-04-03 11:15:00'),

    -- Card 4: Blocked card - all limits set to zero
    (4, 0.00, 0.00, '2025-04-04 13:45:00'),

    -- Card 5: Blocked card - all limits set to zero
    (5, 0.00, 0.00, '2025-04-05 14:10:00'),

    -- Card 6: Expired card with minimal remaining limits
    (6, 100.00, 3000.00, '2025-04-06 16:20:00'),

    -- Card 7: Expired card with very low limits
    (7, 50.00, 1500.00, '2025-04-07 17:30:00'),

    -- Card 8: VIP card with premium limits
    (8, 5000.00, 150000.00, '2025-04-08 08:45:00'),

    -- Card 9: Premium card with enhanced limits
    (9, 3000.00, 90000.00, '2025-04-09 10:15:00'),

    -- Card 10: User's personal card with standard limits
    (10, 200.00, 6000.00, '2025-04-10 12:00:00');
