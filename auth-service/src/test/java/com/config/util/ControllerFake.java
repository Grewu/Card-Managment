package com.config.util;

import com.config.exception.EntityAlreadyExistsException;
import com.config.exception.EntityNotFoundException;
import com.config.exception.InvalidEmailException;
import com.config.exception.InvalidPasswordException;
import com.config.exception.InvalidTokenException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fake")
public class ControllerFake {

  @GetMapping("/entity/{id}")
  ResponseEntity<DtoFake> throwEntityNotFoundException(@PathVariable Long id) {
    throw new EntityNotFoundException(DtoFake.class, id);
  }

  @GetMapping("/email/{email}")
  ResponseEntity<Void> throwInvalidEmailException(@PathVariable String email) {
    throw new InvalidEmailException(email);
  }

  @PostMapping("/token")
  ResponseEntity<Void> invalidTokenException() {
    throw new InvalidTokenException();
  }

  @PostMapping("/password/{password}")
  ResponseEntity<Void> invalidPasswordException(@PathVariable String password) {
    throw new InvalidPasswordException(password);
  }

  @PostMapping("/entity-exist/{id}")
  ResponseEntity<DtoFake> invalidEntityAlreadyExistsException(@PathVariable Long id) {
    throw new EntityAlreadyExistsException(DtoFake.class, String.valueOf(id));
  }

  @PostMapping("/valid")
  ResponseEntity<Void> throwValidationException(@Valid @RequestBody DtoFake dto) {
    return ResponseEntity.ok().build();
  }
}
