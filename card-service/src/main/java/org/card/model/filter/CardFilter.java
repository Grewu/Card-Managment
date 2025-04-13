package org.card.model.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CardFilter(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YYYY_MM_DD_T_HH_MM_SS)
        LocalDateTime expiryDate,
    BigDecimal balance) {
  public static final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
}
