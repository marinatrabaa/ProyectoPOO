package Library.Users;

import Library.lends.UserHistory;

public abstract class User {

    protected String name;
    protected String id;
    protected ContactData data;
    protected UserHistory history = new UserHistory();

    public User(String name, String id, ContactData data){
        this.name = name;
        this.id = id;
        this.data = data;
    }

    public abstract void showType();

    public String getName() {
        return name;
    }

    public ContactData getContact() {
        return data;
    }

    public UserHistory getHistory(){
        return history;
    }

    //inner class for extra data
    public class ContactData{
        String email;
        String phoneNumber;

        public ContactData(String email, String phone){
            this.email = email;
            this.phoneNumber = phone;
        }

        public String getEmail(){
            return email;
        }

        public String getPhoneNumber(){
            return phoneNumber;
        }

    }
    
}
