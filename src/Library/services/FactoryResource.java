package Library.services;

import Library.resources.Book;
import Library.resources.Magazine;
import Library.resources.Resource;

public class FactoryResource {

    public static Resource createResource(String type, String name, String author, String id) {
        return switch (type.toLowerCase()) {
            case "book" -> new Book(name, id, author);
            case "magazine" -> new Magazine(name, id, author);
            default -> throw new IllegalArgumentException("Tipo no soportado");
        };
    }
    
}
