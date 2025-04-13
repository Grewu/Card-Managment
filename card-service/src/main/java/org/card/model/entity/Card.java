package org.card.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.card.model.entity.enums.CardStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards")
@EntityListeners(AuditingEntityListener.class)
public class Card {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "encrypted_number", nullable = false, unique = true)
  private String encryptedNumber;

  @CreatedBy
  @Column(name = "user_email", nullable = false)
  private String userEmail;

  @Column(name = "expiry_date", updatable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDateTime expiryDate;

  @JdbcType(PostgreSQLEnumJdbcType.class)
  @Column(name = "status", nullable = false)
  private CardStatus status;

  @Column(name = "balance", nullable = false)
  private BigDecimal balance;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "sourceCard", cascade = CascadeType.ALL)
  private List<Transaction> transactions;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime createdAt;

  public static class Fields {
    public static final String id = "id";
    public static final String encryptedNumber = "encryptedNumber";
    public static final String userEmail = "userEmail";
    public static final String expiryDate = "expiryDate";
    public static final String status = "status";
    public static final String balance = "balance";
    public static final String transactions = "transactions";
    public static final String createdAt = "createdAt";
  }
}
