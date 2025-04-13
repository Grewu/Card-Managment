CREATE TABLE cards (
                       id BIGSERIAL PRIMARY KEY,
                       encrypted_number VARCHAR(255) NOT NULL UNIQUE,
                       user_email VARCHAR(255) NOT NULL ,
                       expiry_date DATE,
                       status card_status NOT NULL,
                       balance DECIMAL(19, 2)  NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);