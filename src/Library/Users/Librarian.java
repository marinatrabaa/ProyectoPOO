package Library.Users;


public class Librarian extends User{

    private String turn;

    public Librarian(String name, String id, ContactData data, String turn){
        super(name, id, data);
        this.turn = turn;
    }

    public String getTurn(){
        return this.turn;
    }

    public void setTurn(String newTurn){
        this.turn = newTurn;
    }
    
    @Override
    public void showType() {
        System.out.println("User type: Librarian, name: " + name + ", turn: " + turn);
    }
    
}
