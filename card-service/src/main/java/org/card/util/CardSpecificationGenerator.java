package org.card.util;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.card.model.entity.Card;
import org.card.model.filter.CardFilter;
import org.springframework.data.jpa.domain.Specification;

public class CardSpecificationGenerator {

  public static Specification<Card> filterToSpecification(CardFilter filter) {
    return ((root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (filter.expiryDate() != null) {
        predicates.add(cb.equal(root.get("expiryDate"), filter.expiryDate()));
      }

      if (filter.balance() != null) {
        predicates.add(cb.equal(root.get("balance"), filter.balance()));
      }

      return cb.and(predicates.toArray(new Predicate[0]));
    });
  }
}
