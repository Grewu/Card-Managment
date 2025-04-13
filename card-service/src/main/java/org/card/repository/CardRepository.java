package org.card.repository;

import java.util.Optional;
import org.card.model.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository
    extends AbstractRepository<Long, Card>, JpaSpecificationExecutor<Card> {

  boolean existsByEncryptedNumber(String encryptedNumber);

  Page<Card> findAllCardsByUserEmail(String email, Pageable pageable);

  @Query("SELECT c.encryptedNumber FROM Card c WHERE c.userEmail = :userEmail")
  Optional<String> findEncryptedNumberByUserEmail(@Param("userEmail") String userEmail);
}
