package org.card.repository;

import org.card.model.entity.CardLimit;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardLimitsRepository
    extends AbstractRepository<Long, CardLimit>, JpaSpecificationExecutor<CardLimit> {
  boolean existsByCardId(Long cardId);
}
