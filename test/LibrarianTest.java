package test;

import static org.junit.Assert.*;
import org.junit.Test;

import Library.Users.Librarian;
import Library.Users.User.ContactData;

public class LibrarianTest {

    @Test
    public void testTurn() {
        Librarian l = new Librarian("Bob", "2", new ContactData("b@b.com", "222"), "morning");
        assertEquals("morning", l.getTurn());

        l.setTurn("evening");
        assertEquals("evening", l.getTurn());
    }
}
