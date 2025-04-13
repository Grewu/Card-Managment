CREATE TABLE transactions (
                              id UUID PRIMARY KEY,
                              amount DECIMAL(19, 2) NOT NULL,
                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              source_card_id BIGINT NOT NULL,
                              target_card_id BIGINT,
                              type VARCHAR(20) NOT NULL,
                              CONSTRAINT fk_transaction_source_card FOREIGN KEY (source_card_id) REFERENCES cards(id),
                              CONSTRAINT fk_transaction_target_card FOREIGN KEY (target_card_id) REFERENCES cards(id)
);