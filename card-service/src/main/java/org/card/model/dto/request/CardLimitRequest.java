package org.card.model.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CardLimitRequest(
    @NotNull(message = "Card ID is required") Long cardId,
    @NotNull(message = "Daily limit is required")
        @DecimalMin(value = "0.0", message = "Daily limit must be positive or zero")
        BigDecimal dailyLimit,
    @NotNull(message = "Monthly limit is required")
        @DecimalMin(value = "0.0", message = "Monthly limit must be positive or zero")
        BigDecimal monthlyLimit) {}
