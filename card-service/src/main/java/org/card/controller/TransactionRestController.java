package org.card.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.card.model.dto.response.TransactionResponse;
import org.card.model.filter.TransactionFilter;
import org.card.service.api.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = TransactionRestController.TRANSACTION_API_PATH)
public class TransactionRestController {
  protected static final String TRANSACTION_API_PATH = "/api/v0/transactions";
  public static final int PAGE_SIZE = 20;
  private final TransactionService transactionService;

  @GetMapping
  @PreAuthorize("hasAuthority('card:read')")
  public ResponseEntity<Page<TransactionResponse>> getTransactionsByCard(
      @PageableDefault(PAGE_SIZE) Pageable pageable, Long cardId) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(transactionService.getTransactionsByCard(pageable, cardId));
  }

  @GetMapping("/filter")
  @PreAuthorize("hasAuthority('card:read')")
  public ResponseEntity<Page<TransactionResponse>> getAllByFilter(
      @Valid TransactionFilter transactionFilter, @PageableDefault(PAGE_SIZE) Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(transactionService.getAllByFilter(transactionFilter, pageable));
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('card:read')")
  public ResponseEntity<TransactionResponse> getById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(transactionService.getById(id));
  }
}
