package Library.Users;

public class Reader extends User {

    private int limitLoans = 5;

    public Reader(String name, String id, ContactData data){
        super(name, id, data);
    }

    public boolean canLend(){
        return history.getActiveLoans() < limitLoans;
    }

    public void increaseLoans(int extra){
        limitLoans += extra;
    }

    public int getLimit(){
        return limitLoans;
    }

    @Override
    public void showType() {
        System.out.println("User type: Reader, name: " + name);
    }
    
}
