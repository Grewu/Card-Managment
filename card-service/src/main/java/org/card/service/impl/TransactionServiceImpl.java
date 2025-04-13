package org.card.service.impl;

import lombok.RequiredArgsConstructor;
import org.card.exception.EntityNotFoundException;
import org.card.mapper.TransactionMapper;
import org.card.model.dto.response.TransactionResponse;
import org.card.model.entity.Transaction;
import org.card.model.filter.TransactionFilter;
import org.card.repository.TransactionRepository;
import org.card.service.api.TransactionService;
import org.card.util.TransactionSpecificationGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService {

  private final TransactionMapper transactionMapper;
  private final TransactionRepository transactionRepository;

  @Override
  public TransactionResponse create(Void t) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Page<TransactionResponse> getAll(Pageable pageable) {
    return transactionRepository.findAll(pageable).map(transactionMapper::toTransactionResponse);
  }

  @Override
  public TransactionResponse getById(Long id) {
    return transactionRepository
        .findById(id)
        .map(transactionMapper::toTransactionResponse)
        .orElseThrow(() -> new EntityNotFoundException(Transaction.class, id));
  }

  @Override
  public TransactionResponse update(Long id, Void t) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void delete(Long id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Page<TransactionResponse> getTransactionsByCard(Pageable pageable, Long id) {
    return transactionRepository
        .findAllTransactionBySourceCardId(id, pageable)
        .map(transactionMapper::toTransactionResponse);
  }

  @Override
  public Page<TransactionResponse> getAllByFilter(
      TransactionFilter transactionFilter, Pageable pageable) {
    return transactionRepository
        .findAll(
            TransactionSpecificationGenerator.filterToSpecification(transactionFilter), pageable)
        .map(transactionMapper::toTransactionResponse);
  }
}
