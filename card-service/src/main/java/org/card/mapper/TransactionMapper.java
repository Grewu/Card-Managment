package org.card.mapper;

import org.card.model.dto.response.TransactionResponse;
import org.card.model.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
  TransactionResponse toTransactionResponse(Transaction transaction);
}
