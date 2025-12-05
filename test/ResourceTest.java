package test;

import static org.junit.Assert.*;
import org.junit.Test;

import Library.resources.Book;

public class ResourceTest {

    @Test
    public void testLendAndReturn() {
        Book b = new Book("B1", "Dune", "Herbert");

        assertTrue(b.isFree());
        b.lendResource();
        assertFalse(b.isFree());
        b.returnResource();
        assertTrue(b.isFree());
    }
}
