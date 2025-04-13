CREATE TABLE  card_limits  (
                             id BIGSERIAL PRIMARY KEY,
                             card_id BIGINT NOT NULL,
                             daily_limit NUMERIC(19, 2) NOT NULL CHECK (daily_limit >= 0),
                             monthly_limit NUMERIC(19, 2) NOT NULL CHECK (monthly_limit >= 0),
                             updated_at TIMESTAMP,
                             CONSTRAINT fk_card_limit_card FOREIGN KEY (card_id) REFERENCES cards(id),
                             CONSTRAINT uk_card_limit_card UNIQUE (card_id)
);