package org.card.model.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.CreditCardNumber;

public record CardRequest(
    @NotBlank(message = "Card number cannot be blank")
        @CreditCardNumber(message = "Invalid card number")
        @Size(min = 16, max = 19, message = "Card number must be 16-19 digits")
        String number,
    @NotNull(message = "Card status cannot be null")
        @Pattern(
            regexp = "ACTIVE|BLOCKED|EXPIRED",
            message = "Card status must be one of: ACTIVE, BLOCKED, EXPIRED")
        String status,
    @NotBlank(message = "Balance cannot be blank")
        @PositiveOrZero(message = "Balance must be positive or zero")
        @DecimalMin(value = "0.0", message = "Balance must be positive or zero")
        String balance) {
  public static class Fields {
    public static final String number = "number";
    public static final String status = "status";
    public static final String balance = "balance";
  }
}
