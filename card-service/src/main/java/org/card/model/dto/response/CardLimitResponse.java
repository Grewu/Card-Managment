package org.card.model.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CardLimitResponse(
    Long id,
    Long cardId,
    BigDecimal dailyLimit,
    BigDecimal monthlyLimit,
    LocalDateTime updatedAt) {}
