package test;

import static org.junit.Assert.*;
import org.junit.Test;

import Library.Users.Reader;
import Library.Users.User.ContactData;

public class ReaderTest {

    @Test
    public void testCanLend() {
        Reader r = new Reader("Ana", "1", new ContactData("a@a.com", "123"));
        assertTrue(r.canLend());
    }

    @Test
    public void testIncreaseLoanLimit() {
        Reader r = new Reader("Ana", "1", new ContactData("a@a.com", "123"));
        r.increaseLoans(3);
        assertEquals(8, r.getLimit());
    }
}
