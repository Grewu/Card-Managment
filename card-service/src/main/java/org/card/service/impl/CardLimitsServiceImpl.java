package org.card.service.impl;

import lombok.RequiredArgsConstructor;
import org.card.exception.EntityAlreadyExistsException;
import org.card.exception.EntityNotFoundException;
import org.card.mapper.CardLimitsMapper;
import org.card.model.dto.request.CardLimitRequest;
import org.card.model.dto.response.CardLimitResponse;
import org.card.model.entity.CardLimit;
import org.card.repository.CardLimitsRepository;
import org.card.service.api.CardLimitsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CardLimitsServiceImpl implements CardLimitsService {

  private final CardLimitsRepository cardLimitsRepository;
  private final CardLimitsMapper cardLimitsMapper;

  @Override
  public CardLimitResponse create(CardLimitRequest cardLimitRequest) {
    if (cardLimitsRepository.existsByCardId(cardLimitRequest.cardId())) {
      throw new EntityAlreadyExistsException(CardLimit.class, cardLimitRequest.cardId());
    }
    var cardLimit = cardLimitsRepository.save(cardLimitsMapper.toCardLimit(cardLimitRequest));
    return cardLimitsMapper.toCardLimitResponse(cardLimit);
  }

  @Override
  public Page<CardLimitResponse> getAll(Pageable pageable) {
    return cardLimitsRepository.findAll(pageable).map(cardLimitsMapper::toCardLimitResponse);
  }

  @Override
  public CardLimitResponse getById(Long id) {
    return cardLimitsRepository
        .findById(id)
        .map(cardLimitsMapper::toCardLimitResponse)
        .orElseThrow(() -> new EntityNotFoundException(CardLimit.class, id));
  }

  @Override
  public CardLimitResponse update(Long id, CardLimitRequest cardLimitRequest) {
    if (cardLimitsRepository.existsByCardId(cardLimitRequest.cardId())) {
      throw new EntityAlreadyExistsException(CardLimit.class, cardLimitRequest.cardId());
    }
    return cardLimitsRepository
        .findById(id)
        .map(current -> cardLimitsMapper.update(cardLimitRequest, current))
        .map(cardLimitsRepository::save)
        .map(cardLimitsMapper::toCardLimitResponse)
        .orElseThrow(() -> new EntityNotFoundException(CardLimit.class, id));
  }

  @Override
  public void delete(Long id) {
    var cardLimitResponse =
        cardLimitsRepository
            .findById(id)
            .map(cardLimitsMapper::toCardLimitResponse)
            .orElseThrow(() -> new EntityNotFoundException(CardLimit.class, id));
    cardLimitsRepository.deleteById(cardLimitResponse.id());
  }
}
