package com.config.model.entity.enums;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum RoleType {
  ADMIN(Arrays.stream(PrivilegeType.values()).map(PrivilegeType::getGrantedAuthority).toList()),

  USER(
      List.of(
          PrivilegeType.CARD_CREATE.getGrantedAuthority(),
          PrivilegeType.CARD_READ.getGrantedAuthority(),
          PrivilegeType.CARD_DELETE.getGrantedAuthority()));

  private final List<GrantedAuthority> authorities;

  RoleType(List<GrantedAuthority> grantedAuthorities) {
    this.authorities = grantedAuthorities;
  }
}
