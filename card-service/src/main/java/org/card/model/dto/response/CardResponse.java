package org.card.model.dto.response;

import lombok.Builder;

@Builder
public record CardResponse(
    Long id, String number, String userEmail, String expiryDate, String status, String balance) {

  public static class Fields {
    public static final String id = "id";
    public static final String number = "number";
    public static final String userEmail = "userEmail";
    public static final String expiryDate = "expiryDate";
    public static final String status = "status";
    public static final String balance = "balance";
  }
}
