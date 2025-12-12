package Library.lends;

import java.util.LinkedList;

/**
 * Tracks the lending history of a library user.
 * Maintains separate lists for active and finished lends.
 */
public class UserHistory {

    protected int loans;

    private LinkedList<Lend> activeLends;
    private LinkedList<Lend> finishedLends;

    /**
     * Creates a new {@code UserHistory} with empty lists of active and finished lends.
     */
    public UserHistory() {
        activeLends = new LinkedList<>();
        finishedLends = new LinkedList<>();
    }

    /**
     * Adds a lend to the list of active lends.
     *
     * @param l the {@link Lend} to add
     */
    public void addLend(Lend l) {
        activeLends.add(l);
    }

    /**
     * Marks a lend as finished: removes it from active lends and adds it to finished lends.
     *
     * @param l the {@link Lend} to move to finished
     */
    public void deleteLend(Lend l) {
        if (activeLends.remove(l)) {
            finishedLends.add(l);
        }
    }

    /**
     * Returns the list of active lends.
     *
     * @return a {@link LinkedList} of active lends
     */
    public LinkedList<Lend> getActiveLendList() {
        return activeLends;
    }

    /**
     * Returns the list of finished lends.
     *
     * @return a {@link LinkedList} of finished lends
     */
    public LinkedList<Lend> getFinishedLendList() {
        return finishedLends;
    }

    /**
     * Returns the number of active loans.
     *
     * @return the count of active loans
     */
    public int getActiveLoans() {
        return loans;
    }

    /**
     * Prints the user's lending history to the console,
     * including active and finished lends.
     */
    public void showHistory() {
        System.out.println("---- User History ----");
        System.out.println("Active:");
        activeLends.forEach(p -> System.out.println("  - " + p));
        System.out.println("Ended:");
        finishedLends.forEach(p -> System.out.println("  - " + p));
    }
}
