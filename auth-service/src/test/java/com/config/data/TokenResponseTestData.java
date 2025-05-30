package com.config.data;

import com.config.model.dto.response.TokenResponse;
import lombok.Builder;

@Builder(setterPrefix = "with")
public class TokenResponseTestData {
  @Builder.Default private String accessToken = "accessToken";
  @Builder.Default private String refreshToken = "refreshToken";

  public TokenResponse buildTokenResponse() {
    return new TokenResponse(accessToken, refreshToken);
  }
}
