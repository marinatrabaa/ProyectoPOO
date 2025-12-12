package Library.Users;


/**
 * Represents a librarian user in the library system.  
 * A librarian has a work shift (turn) in addition to the common
 * user information inherited from {@link User}.
 */
public class Librarian extends User {

    private String turn;

    /**
     * Creates a new {@code Librarian} with the specified personal data
     * and work shift.
     *
     * @param name the librarian's name
     * @param id   the librarian's unique identifier
     * @param data the contact information of the librarian
     * @param turn the work shift assigned to the librarian
     */
    public Librarian(String name, String id, ContactData data, String turn) {
        super(name, id, data);
        this.turn = turn;
    }

    /**
     * Returns the librarian's work shift.
     *
     * @return the work shift (turn)
     */
    public String getTurn() {
        return this.turn;
    }

    /**
     * Updates the librarian's work shift.
     *
     * @param newTurn the new shift to assign
     */
    public void setTurn(String newTurn) {
        this.turn = newTurn;
    }

    /**
     * Displays the type of user and relevant librarian information.
     */
    @Override
    public void showType() {
        System.out.println("User type: Librarian, name: " + name + ", turn: " + turn);
    }
}
