package Library.lends;

import java.time.LocalDate;
import Library.Users.User;
import Library.resources.Resource;

/**
 * Represents a loan of a {@link Resource} to a {@link User} in the library system.
 * Tracks the start date, finish date, and return status of the loan.
 */
public class Lend {

    private int id;
    private Resource resource;
    private User user;
    private LocalDate startDate;
    private LocalDate finishDate;
    private boolean returned = false;

    /**
     * Creates a new loan for the given resource and user.
     * The loan starts today and has a default duration of 15 days.
     *
     * @param r the resource being loaned
     * @param u the user borrowing the resource
     */
    public Lend(Resource r, User u) {
        this.resource = r;
        this.user = u;
        this.startDate = LocalDate.now();
        this.finishDate = startDate.plusDays(15);
    }

    /**
     * Creates a new loan for the given resource and user with specified start and end dates.
     *
     * @param r     the resource being loaned
     * @param u     the user borrowing the resource
     * @param start the start date of the loan
     * @param end   the finish date of the loan
     */
    public Lend(Resource r, User u, LocalDate start, LocalDate end) {
        this.resource = r;
        this.user = u;
        this.startDate = start;
        this.finishDate = end;
    }

    /**
     * Returns the ID of the loan.
     *
     * @return the loan ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the loan.
     *
     * @param id the loan ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the resource being loaned.
     *
     * @return the {@link Resource} of this loan
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Returns the user who borrowed the resource.
     *
     * @return the {@link User} who borrowed the resource
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the start date of the loan.
     *
     * @return the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the finish date (due date) of the loan.
     *
     * @return the finish date
     */
    public LocalDate getFinishDate() {
        return finishDate;
    }

    /**
     * Checks whether the resource has been returned.
     *
     * @return {@code true} if returned, {@code false} otherwise
     */
    public boolean isReturned() {
        return returned;
    }

    /**
     * Marks the resource as returned and updates the resource's internal state.
     */
    public void checkReturned() {
        this.returned = true;
        resource.returnResource();
    }

    /**
     * Sets the returned status of the loan.
     *
     * @param b {@code true} if the resource is returned, {@code false} otherwise
     */
    public void setReturned(boolean b) {
        returned = b;
    }

    /**
     * Returns a string representation of the loan, including resource, user, start date,
     * due date, and return status.
     *
     * @return a descriptive string of the loan
     */
    @Override
    public String toString() {
        return "Loan of " + resource.getName() +
               " to " + user.getName() +
               " | Start: " + startDate +
               " | Due: " + finishDate +
               " | Returned: " + returned;
    }
}
