package org.card.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card_limits")
@EntityListeners(AuditingEntityListener.class)
public class CardLimit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "card_id", nullable = false)
  private Card card;

  @Column(name = "daily_limit", nullable = false)
  @DecimalMin(value = "0.0", message = "Daily limit must be positive or zero")
  private BigDecimal dailyLimit;

  @Column(name = "monthly_limit", nullable = false)
  @DecimalMin(value = "0.0", message = "Monthly limit must be positive or zero")
  private BigDecimal monthlyLimit;

  @Column(name = "updated_at")
  @LastModifiedDate
  private LocalDateTime updatedAt;

  public static class Fields {
    public static final String id = "id";
  }
}
