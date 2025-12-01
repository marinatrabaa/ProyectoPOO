package Library.Users;

import Library.lends.UserHistory;

public class Reader extends User {

    private int limitLoans = 5;
    private UserHistory history;

    public Reader(String name, String id, ContactData data){
        super(name, id, data);
        this.history = new UserHistory();
    }

    public boolean canLend(){
        return this.history.getActiveLoans() < limitLoans;
    }

    public void increaseLoans(int extra){
        limitLoans += extra;
    }

    public int getLimit(){
        return limitLoans;
    }

    public UserHistory getHistory() {
    return this.history;
    }

    @Override
    public void showType() {
        System.out.println("User type: Reader, name: " + name + "limit: " +  limitLoans);
    }
    
}
