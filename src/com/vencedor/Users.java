package com.vencedor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * All users as ArrayList
 */
public class Users {
   List<User> database;

   public Users() {
      database = new ArrayList<>();
   }

   public boolean add(final User user) {
      for (User item : database) {
         if (item.equals(user)) {
            return false;
         }
      }

      database.add(user);

      return true;
   }

   @Override
   public String toString() {
      return "\nUsers{" + database + "}";
   }

   public boolean isPresent(final String login, final String password) {
      for (User user : database) {
         if (user.getLogin().toLowerCase(Locale.ROOT).equals(login.strip().toLowerCase(Locale.ROOT))) {
            return user.getPasswords().checkActivePassword(password);
         }
      }

      return false;
   }
}