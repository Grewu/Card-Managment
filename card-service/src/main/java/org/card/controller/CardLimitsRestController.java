package org.card.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.card.model.dto.request.CardLimitRequest;
import org.card.model.dto.response.CardLimitResponse;
import org.card.service.api.CardLimitsService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = CardLimitsRestController.LIMIT_API_PATH)
public class CardLimitsRestController {
  protected static final String LIMIT_API_PATH = "/api/v0/limits";
  public static final int PAGE_SIZE = 20;
  private final CardLimitsService cardLimitsService;

  @PostMapping
  @PreAuthorize("hasAuthority('limit:create')")
  public ResponseEntity<CardLimitResponse> create(
      @Valid @RequestBody CardLimitRequest cardLimitRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(cardLimitsService.create(cardLimitRequest));
  }

  @GetMapping
  @PreAuthorize("hasAuthority('limit:read')")
  public ResponseEntity<Page<CardLimitResponse>> getAll(
      @PageableDefault(PAGE_SIZE) Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(cardLimitsService.getAll(pageable));
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('limit:read')")
  public ResponseEntity<CardLimitResponse> getById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(cardLimitsService.getById(id));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('limit:create')")
  public ResponseEntity<CardLimitResponse> update(
      @PathVariable Long id, @Valid @RequestBody CardLimitRequest cardLimitRequest) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(cardLimitsService.update(id, cardLimitRequest));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('limit:delete')")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    cardLimitsService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
