package org.card.service.impl;

import lombok.SneakyThrows;
import org.bouncycastle.crypto.CryptoException;
import org.card.service.api.CardEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

@Service
public class CardEncryptorImpl implements CardEncryptor {

  private final TextEncryptor encryptor;

  public CardEncryptorImpl(
      @Value("${card.encryption.password}") String password,
      @Value("${card.encryption.salt}") String salt) {
    this.encryptor = Encryptors.text(password, salt); // AES-256
  }

  @SneakyThrows
  @Override
  public String encrypt(String cardNumber) {
    try {
      return encryptor.encrypt(cardNumber);
    } catch (Exception e) {
      throw new CryptoException("Encryption failed", e);
    }
  }

  @SneakyThrows
  @Override
  public String decrypt(String encryptedCard) {
    try {
      return encryptor.decrypt(encryptedCard);
    } catch (Exception e) {
      throw new CryptoException("Decryption failed", e);
    }
  }

  @Override
  public String maskCardNumber(String number) {
    try {
      return number.substring(0, 4) + "******" + number.substring(number.length() - 4);
    } catch (Exception e) {
      return "**** **** **** ****";
    }
  }
}
