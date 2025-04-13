package org.card.mapper;

import org.card.model.dto.request.CardLimitRequest;
import org.card.model.dto.response.CardLimitResponse;
import org.card.model.entity.CardLimit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CardLimitsMapper {
  CardLimit toCardLimit(CardLimitRequest cardLimitRequest);

  CardLimitResponse toCardLimitResponse(CardLimit cardLimit);

  @Mapping(target = CardLimit.Fields.id, ignore = true)
  CardLimit update(CardLimitRequest cardLimitRequest, @MappingTarget CardLimit current);
}
