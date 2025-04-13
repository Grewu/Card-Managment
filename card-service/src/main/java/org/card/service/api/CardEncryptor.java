package org.card.service.api;

public interface CardEncryptor {

  String encrypt(String number);

  String decrypt(String encryptedCardNumber);

  String maskCardNumber(String number);
}
