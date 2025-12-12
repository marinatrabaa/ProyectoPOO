package Library.services;

import java.time.LocalDate;

/**
 * Provides notification services for library users.
 * Currently, notifications are simulated by printing messages to the console.
 */
public class Notifier {

    /**
     * Sends an email notification to a user indicating the date on which
     * their loan was completed.
     * <p>
     * This implementation currently prints the message to the console instead of
     * sending a real email.
     *
     * @param message the date when the loan was finished
     * @param user    the name of the user to notify
     */
    public void sendEmail(LocalDate message, String user) {
        System.out.println("Sending email to user: " + user + ", your lend finished at: " + message);
    }
}
