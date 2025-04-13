package org.card.auditor;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserEmailAuditorAware implements AuditorAware<String> {
  @NonNull
  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
  }
}
