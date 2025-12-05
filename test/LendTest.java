package test;

import static org.junit.Assert.*;
import org.junit.Test;

import Library.lends.Lend;
import Library.Users.Reader;
import Library.Users.User.ContactData;
import Library.resources.Book;

public class LendTest {

    @Test
    public void testLendCreation() {
        Reader r = new Reader("Ana", "1", new ContactData("a@a.com", "123"));
        Book b = new Book("B1", "Dune", "Herbert");

        Lend lend = new Lend(b, r);

        assertEquals(r, lend.getUser());
        assertEquals(b, lend.getResource());
        assertFalse(lend.isReturned());
    }

    @Test
    public void testReturn() {
        Reader r = new Reader("Ana", "1", new ContactData("a@a.com", "123"));
        Book b = new Book("B1", "Dune", "Herbert");

        Lend lend = new Lend(b, r);
        lend.checkReturned();

        assertTrue(lend.isReturned());
        assertTrue(b.isFree());
    }
}
