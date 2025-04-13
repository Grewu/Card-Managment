-- ������� ��� ��� ������� �����
CREATE TYPE card_status AS ENUM (
    'ACTIVE',
    'BLOCKED',
    'EXPIRED'
    );

-- ������� ��� ��� ���� ����������
CREATE TYPE transaction_type AS ENUM (
    'WITHDRAWAL',
    'TRANSFER',
    'DEPOSIT'
    );