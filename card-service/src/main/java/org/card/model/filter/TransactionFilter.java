package org.card.model.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import org.card.model.entity.enums.TransactionType;

public record TransactionFilter(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YYYY_MM_DD_T_HH_MM_SS)
        LocalDateTime createdAt,
    TransactionType type) {
  public static final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
}
