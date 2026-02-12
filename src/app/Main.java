package app;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import Library.Users.Librarian;
import Library.Users.Reader;
import Library.Users.User;
import Library.Users.User.ContactData;
import Library.lends.Lend;
import Library.resources.Resource;
import Library.services.FactoryResource;
import Library.services.LibraryManager;
import Library.services.Notifier;
import Library.services.scheduler.ThreadsNotifier;
import Library.services.strategy.AuthorSearch;
import Library.services.strategy.NameSearch;
import dao.LendDAO;
import dao.ResourceDAO;
import dao.UserDAO;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        // 1. INITIALIZATION 
        LibraryManager manager = LibraryManager.getInstance();
        UserDAO userDAO = new UserDAO();
        ResourceDAO resourceDAO = new ResourceDAO();
        Scanner sc = new Scanner(System.in);

        // Pre-load data 
        Reader reader = new Reader("Mario", "L001", new ContactData("mario@gmail.com", "987654212"));
        Librarian admin = new Librarian("Laura", "B001", new ContactData("laura@gmail.com", "123456787"), "mornings");
        
        manager.addUser(reader);
        manager.addUser(admin);

        Resource book1 = FactoryResource.createResource("book", "1984", "George Orwell", "001");
        Resource book2 = FactoryResource.createResource("book", "El Hobbit", "J.R.R. Tolkien", "002");
        Resource magazine = FactoryResource.createResource("magazine", "National Geographic", "company", "003");
        
        manager.addResource(book1);
        manager.addResource(book2);
        manager.addResource(magazine);

        // DAOs for lending
        List<User> userList = new ArrayList<>(manager.getUsers());
        List<Resource> resourceList = new ArrayList<>(manager.getCataloge().values());
        LendDAO lendDAO = new LendDAO(userList, resourceList);

        Lend l1 = new Lend(book1, reader);

        // Start thread
        ThreadsNotifier thread = new ThreadsNotifier(manager, new Notifier());
        thread.start();

        System.out.println("Cleaning database for demo...");
        try {
            lendDAO.deleteAllLends(); 
            userDAO.deleteAllUsers();
            resourceDAO.deleteAllResources();
            System.out.println("Database ready for clean demo.");
        } catch (Exception e) {
            System.out.println("Note: DB was already empty or tables don't exist yet.");
}

        // 2. INTERACTIVE MENU
        int option = -1;
        do {
            System.out.println("\n========================================");
            System.out.println("   LIBRARY MANAGEMENT SYSTEM - MENU");
            System.out.println("========================================");
            System.out.println("1. Show Full Catalogue (Memory)");
            System.out.println("2. Search by Name (Strategy Pattern)");
            System.out.println("3. Search by Author (Strategy Pattern)");
            System.out.println("4. Register Lend & Save to DB");
            System.out.println("5. Return Resource");
            System.out.println("6. Show Database History (Lends)");
            System.out.println("0. Exit");
            System.out.print("\nSelect an option: ");

            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (option) {
                case 1:
                    System.out.println("\n--- CATALOGUE ---");
                    manager.getCataloge().values().forEach(r -> System.out.println("- " + r));
                    break;

                case 2:
                    System.out.print("Enter book name: ");
                    String name = sc.nextLine();
                    manager.search(new NameSearch(), name).forEach(r -> System.out.println("Found: " + r));
                    break;

                case 3:
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    manager.search(new AuthorSearch(), author).forEach(r -> System.out.println("Found: " + r));
                    break;

                case 4:
                    System.out.println("\nRegistering lend for '1984' to Mario...");
                    reader.getHistory().addLend(l1);
                    try {
                        lendDAO.addLend(l1);
                        System.out.println("Lend saved to MySQL.");

                        try {
                            userDAO.saveUser(reader);
                            System.out.println("User saved to MySQL.");
                        } catch (SQLException e) {
                            if (e.getErrorCode() == 1062) { // 1062 code of MySQL for "Duplicate entry"
                                System.out.println("Note: User already exists in DB, skipping save.");
                            } else {
                                throw e;
                            }
                        }

                        resourceDAO.saveResource(book1);
                        System.out.println("Resource updated in DB.");

                    } catch (SQLException | ClassNotFoundException e) {
                        System.out.println("Database error: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("\nReturning '1984'...");
                    l1.checkReturned();
                    try {
                        lendDAO.markAsReturned(l1);
                     } catch (SQLException | ClassNotFoundException e) {
                        System.out.println("Database error: " + e.getMessage());
                    }
                    System.out.println("Resource marked as returned.");
                    break;

                case 6:
                    System.out.println("\n--- LENDS IN DATABASE ---");
                    try {
                        lendDAO.getAllLends().forEach(System.out::println);
                    } catch (Exception e) {
                        System.out.println("Error fetching from DB: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Closing system...");
                    thread.interrupt();
                    break;

                default:
                    System.out.println("Option not available.");
            }

            if (option != 0) {
                System.out.println("\nPress Enter to return to menu...");
                sc.nextLine();
            }

        } while (option != 0);

        sc.close();
    }
}