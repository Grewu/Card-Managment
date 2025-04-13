package org.card.service.api;

import org.card.model.dto.request.CardRequest;
import org.card.model.dto.response.CardResponse;
import org.card.model.dto.response.CardResponseWithTransactions;
import org.card.model.filter.CardFilter;
import org.card.service.AbstractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService extends AbstractService<Long, CardRequest, CardResponse> {
  Page<CardResponse> getAllByFilter(CardFilter cardFilter, Pageable pageable);

  Page<CardResponseWithTransactions> getAllWithTransactions(Pageable pageable);

  void blockCardById(Long cardId);
}
