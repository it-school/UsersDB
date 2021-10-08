package com.vencedor;

public class MainClass {

   public static void main(final String[] args) {
      final Users users = new Users();
      users.add(new User("user", "password1"));
      users.add(new User("admin", "password2"));
      users.add(new User("admin", "password3"));

      System.out.println(users);

      users.database.get(0).setPassword("Qwerty123%");
      System.out.println(users);
      users.database.get(0).setPassword("Qw356w4764ty123*");
      System.out.println(users);

      System.out.println(users.isPresent("User", "Qw356w4764ty123*"));
   }
}
