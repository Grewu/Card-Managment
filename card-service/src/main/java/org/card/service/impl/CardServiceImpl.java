package org.card.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.card.exception.CardAlreadyBlockedException;
import org.card.exception.EntityAlreadyExistsException;
import org.card.exception.EntityNotFoundException;
import org.card.mapper.CardMapper;
import org.card.model.dto.request.CardRequest;
import org.card.model.dto.response.CardResponse;
import org.card.model.dto.response.CardResponseWithTransactions;
import org.card.model.entity.Card;
import org.card.model.entity.enums.CardStatus;
import org.card.model.filter.CardFilter;
import org.card.repository.CardRepository;
import org.card.service.api.CardEncryptor;
import org.card.service.api.CardService;
import org.card.util.CardSpecificationGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CardServiceImpl implements CardService {

  private final CardMapper cardMapper;
  private final CardEncryptor cardEncryptor;
  private final CardRepository cardRepository;
  private final AuditorAware<String> auditorAware;

  @Value("${card.default.expiry-date}")
  private int defaultExpiryMonths;

  @Transactional
  public CardResponse create(CardRequest cardRequest) {
    final String email = getAuthenticatedEmail();

    validateCardUniqueness(cardRequest, email);

    var cardToSave = cardMapper.toCard(cardRequest);
    var encryptedNumber = cardEncryptor.encrypt(cardRequest.number());

    cardToSave.setEncryptedNumber(encryptedNumber);
    cardToSave.setUserEmail(email);
    cardToSave.setExpiryDate(LocalDateTime.now().plusMonths(defaultExpiryMonths));

    var savedCard = cardRepository.save(cardToSave);

    return buildCardResponse(encryptedNumber, savedCard);
  }

  @Override
  public Page<CardResponse> getAll(Pageable pageable) {
    final String email = getAuthenticatedEmail();
    return cardRepository
        .findAllCardsByUserEmail(email, pageable)
        .map(
            card -> {
              var decryptedNumber = cardEncryptor.decrypt(card.getEncryptedNumber());
              var maskedNumber = cardEncryptor.maskCardNumber(decryptedNumber);
              card.setEncryptedNumber(maskedNumber);
              return cardMapper.toCardResponse(card);
            });
  }

  @Override
  public Page<CardResponse> getAllByFilter(CardFilter cardFilter, Pageable pageable) {
    return cardRepository
        .findAll(CardSpecificationGenerator.filterToSpecification(cardFilter), pageable)
        .map(cardMapper::toCardResponse);
  }

  @Override
  public Page<CardResponseWithTransactions> getAllWithTransactions(Pageable pageable) {
    var email =
        auditorAware
            .getCurrentAuditor()
            .orElseThrow(() -> new EntityNotFoundException(Card.class, Card.Fields.userEmail));
    return cardRepository
        .findAllCardsByUserEmail(email, pageable)
        .map(
            card -> {
              var decryptedNumber = cardEncryptor.decrypt(card.getEncryptedNumber());
              var maskedNumber = cardEncryptor.maskCardNumber(decryptedNumber);
              card.setEncryptedNumber(maskedNumber);
              return cardMapper.toCardResponseWithTransaction(card);
            });
  }

  @Override
  public void blockCardById(Long cardId) {
    var card =
        cardRepository
            .findById(cardId)
            .orElseThrow(() -> new EntityNotFoundException(Card.class, cardId));

    if (card.getStatus().equals(CardStatus.BLOCKED)) {
      throw new CardAlreadyBlockedException(Card.class, cardId);
    }

    card.setStatus(CardStatus.BLOCKED);
    cardRepository.save(card);
  }

  @Override
  public CardResponse getById(Long id) {
    return cardRepository
        .findById(id)
        .map(cardMapper::toCardResponse)
        .orElseThrow(() -> new EntityNotFoundException(Card.class, id));
  }

  @Override
  @Transactional
  public CardResponse update(Long id, CardRequest cardRequest) {
    String encryptedNumber = cardRequest.number();
    if (cardRepository.existsByEncryptedNumber(encryptedNumber)) {
      throw new EntityAlreadyExistsException(Card.class, encryptedNumber);
    }
    return cardRepository
        .findById(id)
        .map(current -> cardMapper.update(cardRequest, current))
        .map(cardRepository::save)
        .map(cardMapper::toCardResponse)
        .orElseThrow(() -> new EntityNotFoundException(Card.class, id));
  }

  @Override
  @Transactional
  public void delete(Long id) {
    var cardResponse =
        cardRepository
            .findById(id)
            .map(cardMapper::toCardResponse)
            .orElseThrow(() -> new EntityNotFoundException(Card.class, id));
    cardRepository.deleteById(cardResponse.id());
  }

  private String getAuthenticatedEmail() {
    return auditorAware
        .getCurrentAuditor()
        .orElseThrow(() -> new EntityNotFoundException(Card.class, Card.Fields.userEmail));
  }

  private void validateCardUniqueness(CardRequest cardRequest, String email) {
    var encryptedNumbers = cardRepository.findEncryptedNumberByUserEmail(email);

    if (encryptedNumbers.isEmpty()) {
      throw new EntityNotFoundException(Card.class, Card.Fields.userEmail);
    }
    if (encryptedNumbers.stream()
        .anyMatch(num -> cardEncryptor.decrypt(num).equalsIgnoreCase(cardRequest.number()))) {
      throw new EntityAlreadyExistsException(Card.class, email);
    }
  }

  private CardResponse buildCardResponse(String encryptedNumber, Card savedCard) {
    var maskedNumber = cardEncryptor.maskCardNumber(encryptedNumber);
    var formattedExpiryDate =
        savedCard.getExpiryDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

    return new CardResponse(
        savedCard.getId(),
        maskedNumber,
        savedCard.getUserEmail(),
        formattedExpiryDate,
        savedCard.getStatus().name(),
        String.format("%.2f", savedCard.getBalance()));
  }
}
