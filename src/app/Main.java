package app;

import Library.Users.Librarian;
import Library.Users.Reader;
import Library.Users.User.ContactData;
import Library.lends.Lend;
import Library.resources.Resource;
import Library.services.FactoryResource;
import Library.services.LibraryManager;
import Library.services.Notifier;
import Library.services.scheduler.ThreadsNotifier;
import Library.services.strategy.AuthorSearch;
import Library.services.strategy.NameSearch;

public class Main {

    public static void main(String[] args) {
        
        LibraryManager manager = LibraryManager.getInstance();

        System.out.println("-- START OF LIBRARY SYSTEM --");

        Reader reader = new Reader("Mario", "L001", new ContactData("mario@gmail.com", "987654212"));
        Librarian admin = new Librarian("Laura", "B001", new ContactData("laura@gmail.com", "123456787"), "mornings");
        manager.addUser(reader);
        manager.addUser(admin);

        Resource book1 = FactoryResource.createResource("book", "1984", "George Orwell", "001");
        Resource book2 = FactoryResource.createResource("book", "El Hobbit", "J.R.R. Tolkien", "002");
        Resource magazine = FactoryResource.createResource("magazine", "National Geographic", "company", "003");

        System.out.println("The librarian works at " + admin.getTurn());

        manager.addResource(book1);
        manager.addResource(book2);
        manager.addResource(magazine);

        System.out.println("\n=== Name search: '1984' ===");
        manager.search(new NameSearch(), "1984")
              .forEach(r -> System.out.println("- " + r));

        System.out.println("\n=== Author search: 'Tolkien' ===");
        manager.search(new AuthorSearch(), "Tolkien")
              .forEach(r -> System.out.println("- " + r));


        System.out.println("\n===  LENDS AND RETURNS ===");

        //Reader tries to borrow "1984"
        System.out.println("\nTrying to lend '1984' to Mario...");
        Lend l1 = new Lend(book1, reader);
        reader.getHistory().addLend(l1);

        if (l1 != null) {
            System.out.println("New Lend added:");
            System.out.println(" - Resource: " + l1.getResource().getName());
            System.out.println(" - Limit Date: " + l1.getFinishDate());
        } else {
            System.out.println("No se pudo realizar el préstamo.");
        }

        // Attempt to lend an already lent book
        System.out.println("\nTrying to lend same resource: '1984' a Mario...");
        if (!l1.isReturned()) {
            System.out.println("Correct: the system prevented lending a book that is already lent.");
        }

        // Return
        System.out.println("\nReturning '1984'...");
        l1.checkReturned();
        reader.getHistory().deleteLend(l1);
        if (l1.isReturned()) {
            System.out.println("Return successful.");
        } else {
            System.out.println("Error returning the resource.");
        }

        // View reader's historys
        System.out.println("\nmario's lending history:");
        reader.getHistory().getFinishedLendList().forEach(pr ->
            System.out.println(" - " + pr.getResource().getName() +
                               " | returned: " + pr.isReturned()));

        System.out.println("\nStarting thread...");
        ThreadsNotifier thread = new ThreadsNotifier(manager, new Notifier());
        thread.start();

        // IMPORTANT: only for testing, stop the thread after a few seconds
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println("Reminder thread stopped.");

        System.out.println("\n=== END OF DEMO ===");
    }

}
