package org.card.service.api;

import org.card.model.dto.response.TransactionResponse;
import org.card.model.filter.TransactionFilter;
import org.card.service.AbstractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService extends AbstractService<Long, Void, TransactionResponse> {
  Page<TransactionResponse> getTransactionsByCard(Pageable pageable, Long id);

  Page<TransactionResponse> getAllByFilter(TransactionFilter transactionFilter, Pageable pageable);
}
