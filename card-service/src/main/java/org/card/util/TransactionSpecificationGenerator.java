package org.card.util;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.card.model.entity.Transaction;
import org.card.model.filter.TransactionFilter;
import org.springframework.data.jpa.domain.Specification;

public class TransactionSpecificationGenerator {

  public static Specification<Transaction> filterToSpecification(TransactionFilter filter) {
    return ((root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (filter.createdAt() != null) {
        predicates.add(cb.equal(root.get("createdAt"), filter.createdAt()));
      }

      if (filter.type() != null) {
        predicates.add(cb.equal(root.get("type"), filter.type()));
      }

      return cb.and(predicates.toArray(new Predicate[0]));
    });
  }
}
