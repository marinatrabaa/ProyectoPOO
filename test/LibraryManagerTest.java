package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Library.services.LibraryManager;
import Library.resources.Book;

public class LibraryManagerTest {

    private LibraryManager manager;

    @Before
    public void setUp() {
        manager = LibraryManager.getInstance();
        manager.getCataloge().clear();
        manager.getUsers().clear();
    }

    @Test
    public void testSingletonInstance() {
        LibraryManager m2 = LibraryManager.getInstance();
        assertSame(manager, m2);
    }

    @Test
    public void testAddResource() {
        Book b = new Book("B1", "Name", "Author");
        manager.addResource(b);
        assertEquals(1, manager.getCataloge().size());
    }

    @Test
    public void testSearch() {
        Book b = new Book("B1", "Dune", "Herbert");
        manager.addResource(b);

        assertTrue(manager.getCataloge().containsKey("B1"));
    }
}
