package test;

import static org.junit.Assert.*;
import org.junit.Test;

import Library.services.FactoryResource;
import Library.resources.*;

public class FactoryResourceTest {

    @Test
    public void testCreateBook() {
        Resource r = FactoryResource.createResource("book", "Dune", "Herbert", "B1");
        assertTrue(r instanceof Book);
    }

    @Test
    public void testCreateMagazine() {
        Resource r = FactoryResource.createResource("magazine", "Time", "Editorial", "M1");
        assertTrue(r instanceof Magazine);
    }
}
