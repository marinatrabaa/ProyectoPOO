package test;

import static org.junit.Assert.*;
import org.junit.Test;

import Library.resources.Book;

public class BookTest {

    @Test
    public void testBookFields() {
        Book b = new Book("B1", "Dune", "Herbert");

        assertEquals("Dune", b.getName());
        assertEquals("B1", b.getId());
        assertEquals("Herbert", b.getAuthor());
    }
}
