// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package Library.Users;

import Library.lends.UserHistory;

public abstract class User {
   protected String name;
   protected String id;
   protected ContactData data;
   protected UserHistory history = new UserHistory();

   public User(String var1, String var2, ContactData var3) {
      this.name = var1;
      this.id = var2;
      this.data = var3;
   }

   public abstract void showType();

   public String getName() {
      return this.name;
   }

   public String getId(){
    return id;
   }

   public ContactData getContact() {
      return this.data;
   }

   public UserHistory getHistory() {
      return this.history;
   }

    public static class ContactData {
        private String email;
        private String telefono;

        public ContactData(String email, String telefono) {
            this.email = email;
            this.telefono = telefono;
        }

        public String getEmail() { return email; }
        public String getTelefono() { return telefono; }

        @Override
        public String toString() {
            return "Email: " + email + ", Teléfono: " + telefono;
        }
    }
}
