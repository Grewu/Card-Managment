package org.card.model.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.card.model.entity.enums.TransactionType;

public record TransactionResponse(
    UUID id,
    BigDecimal amount,
    LocalDateTime createdAt,
    Long sourceCardId,
    Long targetCardId,
    TransactionType type) {}
