package com.vencedor;

import java.util.ArrayList;
import java.util.List;

/**
 * Passwords class, containing all user passwords
 */
public class Passwords {
   public static final int MIN_HASH_DIFFERENCE = 1000;
   List<Password> passwords;

   public Passwords() {
      this.passwords = new ArrayList<>();
   }

   public boolean add(final String password) {
      for (Password item : passwords) {
         if (item.equals(password) || Math.abs(Math.abs(item.hashCode()) - Math.abs(password.hashCode())) < MIN_HASH_DIFFERENCE)
            return false;
      }

      if (!passwords.isEmpty())
         passwords.get(passwords.size() - 1).setActivePassword(false);
      passwords.add(new Password(password));
      passwords.get(passwords.size() - 1).setActivePassword(true);

      return true;
   }

   @Override
   public String toString() {
      return passwords + "\n";
   }

   public boolean checkActivePassword(final String password) {
      return this.passwords.get(passwords.size() - 1).getPassword().equals(password);
   }
}