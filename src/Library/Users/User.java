package Library.Users;

import Library.lends.UserHistory;

/**
 * Abstract class representing a generic user in the library system.
 * Each user has a name, a unique identifier, contact information,
 * and a borrowing history.
 */
public abstract class User {

   protected String name;
   protected String id;
   protected ContactData data;
   protected UserHistory history = new UserHistory();

   /**
    * Creates a new user with the specified personal data.
    *
    * @param name the user's name
    * @param id   the unique identifier for the user
    * @param data the user's contact information
    */
   public User(String name, String id, ContactData data) {
      this.name = name;
      this.id = id;
      this.data = data;
   }

   /**
    * Abstract method to display the type of user.
    * Must be implemented by subclasses.
    */
   public abstract void showType();

   /**
    * Returns the user's name.
    *
    * @return the name of the user
    */
   public String getName() {
      return this.name;
   }

   /**
    * Returns the user's unique identifier.
    *
    * @return the ID of the user
    */
   public String getId() {
      return this.id;
   }

   /**
    * Returns the user's contact information.
    *
    * @return the {@link ContactData} object
    */
   public ContactData getContact() {
      return this.data;
   }

   /**
    * Returns the user's borrowing history.
    *
    * @return the {@link UserHistory} object
    */
   public UserHistory getHistory() {
      return this.history;
   }

   /**
    * Represents the contact information of a user.
    * Contains an email and a phone number.
    */
   public static class ContactData {

      private String email;
      private String telefono;

      /**
       * Creates a new ContactData instance with the given email and phone.
       *
       * @param email    the user's email address
       * @param telefono the user's phone number
       */
      public ContactData(String email, String telefono) {
         this.email = email;
         this.telefono = telefono;
      }

      /**
       * Returns the email address of the user.
       *
       * @return the email
       */
      public String getEmail() {
         return email;
      }

      /**
       * Returns the phone number of the user.
       *
       * @return the phone number
       */
      public String getTelefono() {
         return telefono;
      }

      /**
       * Returns a string representation of the contact data.
       *
       * @return a string containing email and phone number
       */
      @Override
      public String toString() {
         return "Email: " + email + ", Teléfono: " + telefono;
      }
   }
}
