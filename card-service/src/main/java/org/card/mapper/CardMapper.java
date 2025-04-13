package org.card.mapper;

import org.card.model.dto.request.CardRequest;
import org.card.model.dto.response.CardResponse;
import org.card.model.dto.response.CardResponseWithTransactions;
import org.card.model.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CardMapper {

  @Mapping(target = Card.Fields.id, ignore = true)
  @Mapping(target = Card.Fields.encryptedNumber, source = CardRequest.Fields.number)
  @Mapping(target = Card.Fields.userEmail, ignore = true)
  @Mapping(target = Card.Fields.expiryDate, ignore = true)
  @Mapping(target = Card.Fields.status, source = CardRequest.Fields.status)
  @Mapping(target = Card.Fields.balance, source = CardRequest.Fields.balance)
  @Mapping(target = Card.Fields.transactions, ignore = true)
  @Mapping(target = Card.Fields.createdAt, ignore = true)
  Card toCard(CardRequest cardRequest);

  @Mapping(target = CardResponse.Fields.id, source = Card.Fields.id)
  @Mapping(target = CardResponse.Fields.number, source = Card.Fields.encryptedNumber)
  @Mapping(target = CardResponse.Fields.userEmail, source = Card.Fields.userEmail)
  @Mapping(target = CardResponse.Fields.status, source = Card.Fields.status)
  @Mapping(target = CardResponse.Fields.expiryDate, source = Card.Fields.expiryDate)
  @Mapping(target = CardResponse.Fields.balance, source = Card.Fields.balance)
  CardResponse toCardResponse(Card card);

  @Mapping(target = Card.Fields.id, ignore = true)
  Card update(CardRequest cardRequest, @MappingTarget Card current);

  CardResponseWithTransactions toCardResponseWithTransaction(Card card);
}
