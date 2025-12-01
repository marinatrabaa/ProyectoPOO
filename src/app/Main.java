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

        // Lector intenta pedir prestado "1984"
        System.out.println("\nIntentando prestar '1984' a Mario...");
        Lend l1 = new Lend(book1, reader);
        Lend l3 = new Lend(magazine, reader);
        reader.getHistory().addLend(l1);

        if (l1 != null) {
            System.out.println("New Lend added:");
            System.out.println(" - Resource: " + l1.getResource().getName());
            System.out.println(" - Limit Date: " + l1.getFinishDate());
        } else {
            System.out.println("No se pudo realizar el préstamo.");
        }

        // Intentar prestar un libro ya prestado
        System.out.println("\nTrying to lend same resource: '1984' a Mario...");
        if (!l1.isReturned()) {
            System.out.println("Correcto: el sistema impidió prestar un libro ya prestado.");
        }

        // Devolución
        System.out.println("\nDevolviendo '1984'...");
        l1.checkReturned();
        reader.getHistory().deleteLend(l1);
        if (l1.isReturned()) {
            System.out.println("Devolución exitosa.");
        } else {
            System.out.println("Error al devolver el recurso.");
        }

        // Ver historial del lector
        System.out.println("\nHistorial de préstamos de Mario:");
        reader.getHistory().getFinishedLendList().forEach(pr ->
            System.out.println(" - " + pr.getResource().getName() +
                               " | devuelto: " + pr.isReturned()));

        System.out.println("\nStarting thread...");
        ThreadsNotifier thread = new ThreadsNotifier(manager, new Notifier());
        thread.start();

        // IMPORTANTE: solo para pruebas, detener el hilo tras unos segundos
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println("Hilo de recordatorios detenido.");

        System.out.println("\n=== FIN DE LA DEMO ===");
    }

}
