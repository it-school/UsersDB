package com.vencedor;

import java.util.Locale;
import java.util.Objects;

/**
 * Single user
 */
public class User {
   private final Passwords passwords;
   private String login;

   public User(final String login, final String password) {
      this.setLogin(login);
      this.passwords = new Passwords();
      this.passwords.add(password);
   }

   public String getLogin() {
      return login;
   }

   final public void setLogin(final String login) {
      this.login = login.strip().toLowerCase(Locale.ROOT);
   }

   Passwords getPasswords() {
      return this.passwords;
   }

   public void setPassword(final String password) {
      this.passwords.add(password);
   }

   @Override
   public String toString() {
      return "\n" + "login='" + login + "', passwords: " + passwords;
   }

   @Override
   public boolean equals(final Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      User that = (User) o;
      return this.getLogin().equalsIgnoreCase(that.getLogin());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getLogin(), getPasswords());
   }
}