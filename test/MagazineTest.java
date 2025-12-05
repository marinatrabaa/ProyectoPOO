package test;

import static org.junit.Assert.*;
import org.junit.Test;

import Library.resources.Magazine;

public class MagazineTest {

    @Test
    public void testMagazineFields() {
        Magazine m = new Magazine("Time", "M1", "Editorial");

        assertEquals("Time", m.getName());
        assertEquals("M1", m.getId());
        assertEquals("Editorial", m.getAuthor());
    }
}
