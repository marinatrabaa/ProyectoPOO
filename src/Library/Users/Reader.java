package Library.Users;

import Library.lends.UserHistory;

/**
 * Represents a library reader (borrower) in the system.  
 * A reader has a limit on the number of active loans and
 * keeps a {@link UserHistory} to track borrowing activity.
 */
public class Reader extends User {

    private int limitLoans = 5;
    private UserHistory history;

    /**
     * Creates a new {@code Reader} with the specified personal data.
     * Initializes the borrowing history.
     *
     * @param name the reader's name
     * @param id   the reader's unique identifier
     * @param data the contact information of the reader
     */
    public Reader(String name, String id, ContactData data) {
        super(name, id, data);
        this.history = new UserHistory();
    }

    /**
     * Checks if the reader can borrow more resources.
     *
     * @return {@code true} if the number of active loans is below the limit, {@code false} otherwise
     */
    public boolean canLend() {
        return this.history.getActiveLoans() < limitLoans;
    }

    /**
     * Increases the maximum number of loans the reader can have.
     *
     * @param extra the number of additional loans allowed
     */
    public void increaseLoans(int extra) {
        limitLoans += extra;
    }

    /**
     * Returns the current loan limit for the reader.
     *
     * @return the maximum number of active loans
     */
    public int getLimit() {
        return limitLoans;
    }

    /**
     * Returns the borrowing history of the reader.
     *
     * @return the {@link UserHistory} object
     */
    public UserHistory getHistory() {
        return this.history;
    }

    /**
     * Displays the type of user and relevant reader information.
     */
    @Override
    public void showType() {
        System.out.println("User type: Reader, name: " + name + " limit: " + limitLoans);
    }
}

