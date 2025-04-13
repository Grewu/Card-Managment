package org.card.model.dto.response;

import java.util.List;

public record CardResponseWithTransactions(
    Long id,
    String encryptedNumber,
    String userEmail,
    String expiryDate,
    String status,
    String balance,
    List<TransactionResponse> transactions) {}
