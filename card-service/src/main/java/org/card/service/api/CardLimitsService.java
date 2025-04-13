package org.card.service.api;

import org.card.model.dto.request.CardLimitRequest;
import org.card.model.dto.response.CardLimitResponse;
import org.card.service.AbstractService;

public interface CardLimitsService
    extends AbstractService<Long, CardLimitRequest, CardLimitResponse> {}
