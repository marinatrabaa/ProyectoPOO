package test;

import org.junit.Test;

import app.Main;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testMainRunsWithoutErrors() {
        try {
            Main.main(new String[]{});
            assertTrue(true); // si llegamos aquí, todo bien
        } catch (Exception e) {
            fail("Main.main lanzó una excepción: " + e.getMessage());
        }
    }
}
