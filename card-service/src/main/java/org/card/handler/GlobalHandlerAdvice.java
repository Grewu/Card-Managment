package org.card.handler;

import java.util.Optional;
import java.util.stream.Collectors;
import org.card.exception.AbstractExceptionMessageException;
import org.card.exception.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The {@code GlobalHandlerAdvice} class is a global exception handler for REST controllers,
 * responsible for managing and handling exceptions thrown during the processing of web requests.
 *
 * <p>This class leverages Spring's {@code @RestControllerAdvice} to provide centralized exception
 * handling across all controllers. It captures specific exceptions and returns structured error
 * responses to the clients.
 */
@RestControllerAdvice
public class GlobalHandlerAdvice {

  @ExceptionHandler(AbstractExceptionMessageException.class)
  public ResponseEntity<ExceptionMessage> handle(AbstractExceptionMessageException e) {
    return Optional.of(e)
        .map(AbstractExceptionMessageException::getExceptionMessage)
        .map(exceptionMessage -> ResponseEntity.status(e.getStatusCode()).body(exceptionMessage))
        .orElseThrow();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionMessage> handleThrowable(MethodArgumentNotValidException e) {
    var message =
        e.getFieldErrors().stream()
            .map(error -> error.getField() + " = " + error.getDefaultMessage())
            .collect(Collectors.joining("; "));

    if (message.isEmpty()) {
      message = HttpStatus.BAD_REQUEST.toString();
    }

    var responseError = new ExceptionMessage(HttpStatus.BAD_REQUEST, message);

    return ResponseEntity.badRequest().body(responseError);
  }
}
