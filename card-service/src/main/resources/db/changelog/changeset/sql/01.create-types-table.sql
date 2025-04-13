-- Создаем тип для статуса карты
CREATE TYPE card_status AS ENUM (
    'ACTIVE',
    'BLOCKED',
    'EXPIRED'
    );

-- Создаем тип для типа транзакции
CREATE TYPE transaction_type AS ENUM (
    'WITHDRAWAL',
    'TRANSFER',
    'DEPOSIT'
    );