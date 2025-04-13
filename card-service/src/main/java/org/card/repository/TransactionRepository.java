package org.card.repository;

import org.card.model.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository
    extends AbstractRepository<Long, Transaction>, JpaSpecificationExecutor<Transaction> {
  Page<Transaction> findAllTransactionBySourceCardId(Long id, Pageable pageable);
}
