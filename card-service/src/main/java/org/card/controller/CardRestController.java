package org.card.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.card.model.dto.request.CardRequest;
import org.card.model.dto.response.CardResponse;
import org.card.model.dto.response.CardResponseWithTransactions;
import org.card.model.filter.CardFilter;
import org.card.service.api.CardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = CardRestController.CARD_API_PATH)
public class CardRestController {

  protected static final String CARD_API_PATH = "/api/v0/cards";
  public static final int PAGE_SIZE = 20;
  private final CardService cardService;

  @PostMapping
  @PreAuthorize("hasAuthority('user:write')")
  public ResponseEntity<CardResponse> create(@Valid @RequestBody CardRequest cardRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(cardService.create(cardRequest));
  }

  @GetMapping("/transactions")
  @PreAuthorize("hasAuthority('card:read')")
  public ResponseEntity<Page<CardResponseWithTransactions>> getAllCardsAddTransactions(
      @PageableDefault(PAGE_SIZE) Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(cardService.getAllWithTransactions(pageable));
  }

  @GetMapping("/filter")
  @PreAuthorize("hasAuthority('card:read')")
  public ResponseEntity<Page<CardResponse>> getAllByFilter(
      @Valid CardFilter cardFilter, @PageableDefault(PAGE_SIZE) Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(cardService.getAllByFilter(cardFilter, pageable));
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('card:read')")
  public ResponseEntity<CardResponse> getById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(cardService.getById(id));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('user:write')")
  public ResponseEntity<CardResponse> update(
      @PathVariable Long id, @Valid @RequestBody CardRequest cardRequest) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(cardService.update(id, cardRequest));
  }

  @PatchMapping("/{id}/block")
  @PreAuthorize("hasAuthority('card:block')")
  public ResponseEntity<Void> blockCard(@PathVariable Long id) {
    cardService.blockCardById(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('user:delete')")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    cardService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
