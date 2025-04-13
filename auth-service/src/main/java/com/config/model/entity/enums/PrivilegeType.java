package com.config.model.entity.enums;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public enum PrivilegeType {
  CARD_CREATE(new SimpleGrantedAuthority("card:create")),
  CARD_READ(new SimpleGrantedAuthority("card:read")),
  CARD_DELETE(new SimpleGrantedAuthority("card:delete")),
  CARD_BLOCK(new SimpleGrantedAuthority("card:block")),

  LIMIT_CREATE(new SimpleGrantedAuthority("limit:create")),
  LIMIT_READ(new SimpleGrantedAuthority("limit:read")),
  LIMIT_DELETE(new SimpleGrantedAuthority("limit:delete")),

  USER_READ(new SimpleGrantedAuthority("user:read")),
  USER_WRITE(new SimpleGrantedAuthority("user:write")),
  USER_DELETE(new SimpleGrantedAuthority("user:delete"));

  private final GrantedAuthority grantedAuthority;

  PrivilegeType(GrantedAuthority grantedAuthority) {
    this.grantedAuthority = grantedAuthority;
  }
}
