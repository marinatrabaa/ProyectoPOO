package test;

import static org.junit.Assert.*;
import org.junit.Test;

import Library.lends.*;
import Library.resources.Book;
import Library.Users.Reader;
import Library.Users.User.ContactData;

public class UserHistoryTest {

    @Test
    public void testAddAndDeleteLend() {
        Reader r = new Reader("Ana", "1", new ContactData("a@a.com", "123"));
        Book b = new Book("B1", "Dune", "Herbert");
        Lend lend = new Lend(b, r);

        UserHistory h = new UserHistory();
        h.addLend(lend);

        assertEquals(1, h.getActiveLendList().size());

        h.deleteLend(lend);
        assertEquals(0, h.getActiveLendList().size());
        assertEquals(1, h.getFinishedLendList().size());
    }
}
