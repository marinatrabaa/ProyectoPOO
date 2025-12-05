package test;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

import Library.resources.*;
import Library.services.strategy.*;

public class SearchStrategiesTest {

    @Test
    public void testNameSearch() {
        Resource r = new Book("B1", "Dune", "Herbert");
        NameSearch ns = new NameSearch();

        List<Resource> list = Arrays.asList(r);
        var result = ns.search(list, "Dune");

        assertEquals(1, result.size());
    }

    @Test
    public void testAuthorSearch() {
        Resource r = new Book("B1", "Dune", "Herbert");
        AuthorSearch as = new AuthorSearch();

        List<Resource> list = Arrays.asList(r);
        var result = as.search(list, "Herbert");

        assertEquals(1, result.size());
    }
}
