package Library.Users;

import Library.resources.Resource;

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
/* 
    public void registerResource(Resource r){
        System.out.println("Librarian: " + name + " is registring the resource " + r.getName());
    }
    
    public void removeResource(Resource r){
        System.out.println("Librarian: " + name + " is removing the resource " + r.getName());
    }
*/
    @Override
    public void showType() {
        System.out.println("User type: Librarian, name: " + name + ", turn: " + turn);
    }
    
}
