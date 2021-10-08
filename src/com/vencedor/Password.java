package com.vencedor;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;

/**
 * Password class
 */
public class Password {
   public static final int MIN_PASSWORD_LENGTH = 8;
   private String password;
   private boolean isActivePassword;

   public Password(final String password) {
      this.setPassword(password);
   }

   public boolean isActive() {
      return isActivePassword;
   }

   /**
    * Changes password state
    *
    * @param activePassword whether active or not
    */
   void setActivePassword(final boolean activePassword) {
      isActivePassword = activePassword;
   }

   String getPassword() {
      return password;
   }

   final void setPassword(final String password) {
      this.password = this.isSafePassword(password) ? password : generatePassword();
   }

   private String generatePassword() {
      final Random random = new Random();
      final String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      final String lowerCase = upperCase.toLowerCase(Locale.ROOT);
      final String digits = "0123456789";
      final String otherSymbols = "!\"#$%&'()*+,-./:;<=>?[\\]^_{|}~@`";

      StringBuilder password = new StringBuilder("");
      for (int i = 0; i < 2; i++) {
         password.append(upperCase.charAt(random.nextInt(upperCase.length() - 1)));
         password.append(lowerCase.charAt(random.nextInt(lowerCase.length() - 1)));
         password.append(digits.charAt(random.nextInt(digits.length() - 1)));
         password.append(otherSymbols.charAt(random.nextInt(otherSymbols.length() - 1)));
      }

      int position1;
      int position2;
      char tempChar1;
      char tempChar2;
      for (int i = 0; i < 20 + random.nextInt(30); i++) {
         position1 = random.nextInt(password.length() - 1);
         position2 = random.nextInt(password.length() - 1);

         tempChar1 = password.charAt(position1);
         tempChar2 = password.charAt(position2);
         password.deleteCharAt(position1);
         password.insert(position1, tempChar2);
         password.deleteCharAt(position2);
         password.insert(position2, tempChar1);
      }

      return password.toString();
   }

   @Override
   public String toString() {
      return "password='" + password + "', isActive=" + isActivePassword;
   }

   private boolean isSafePassword(final String password) {
      boolean hasUpperCase = false;
      boolean hasLowerCase = false;
      boolean hasDigit = false;
      boolean hasNonDigitOrLetter = false;
      boolean result = false;

      if (password.length() >= MIN_PASSWORD_LENGTH) {
         for (int i = 0; i < password.length(); i++) {
            if (!hasUpperCase && Character.isUpperCase(password.charAt(i)))
               hasUpperCase = true;
            if (!hasLowerCase && Character.isLowerCase(password.charAt(i)))
               hasLowerCase = true;
            if (!hasDigit && Character.isDigit(password.charAt(i)))
               hasDigit = true;
            if (!hasNonDigitOrLetter && !Character.isLetter(password.charAt(i)) && !Character.isDigit(password.charAt(i)))
               hasNonDigitOrLetter = true;
            if (hasUpperCase && hasLowerCase && hasDigit && hasNonDigitOrLetter)
               result = true;
         }
      }

      return result;
   }

   @Override
   public boolean equals(final Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      final Password password1 = (Password) o;
      return Objects.equals(getPassword(), password1.getPassword());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getPassword());
   }
}