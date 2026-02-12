package Library.services.scheduler;

import java.util.HashSet;
import java.util.Set;
import Library.Users.Reader;
import Library.Users.User;
import Library.lends.Lend;
import Library.services.LibraryManager;
import Library.services.Notifier;

/**
 * Thread that periodically checks for completed loans in the library system
 * and notifies readers via a {@link Notifier}.
 *
 * Every 5 seconds, this thread scans all users, identifies finished loans,
 * and if they have not been notified yet, prints a message to the console and
 * sends an email notification.
 */
public class ThreadsNotifier extends Thread {

    private LibraryManager manager;
    private Notifier notifier;

    /**
     * Creates a new {@code ThreadsNotifier} thread.
     *
     * @param m the {@link LibraryManager} managing users and resources
     * @param n the {@link Notifier} used to send notifications
     */
    public ThreadsNotifier(LibraryManager m, Notifier n) {
        this.manager = m;
        this.notifier = n;
    }

    /**
     * The main thread task.
     *
     * Runs in a loop until the thread is interrupted. Every 5 seconds,
     * it checks all readers for finished loans. If a loan has not been notified yet,
     * it prints a message and sends an email via {@code notifier}.
     */
    @Override
    public void run() {
        Set<Lend> notifiedLends = new HashSet<>();

        while (!Thread.currentThread().isInterrupted()) {
            try {
                sleep(5000);

                for (User u : manager.getUsers()) {
                    if (u instanceof Reader reader) {
                        for (Lend l : reader.getHistory().getFinishedLendList()) {
                            if (!notifiedLends.contains(l)) {
                                System.out.println("There are completed loans. Name: " 
                                        + l.getResource().getName() + " | Reader: " 
                                        + reader.getName());
                                notifier.sendEmail(l.getFinishDate(), reader.getName());
                            }
                            notifiedLends.add(l);
                        }
                    }
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
