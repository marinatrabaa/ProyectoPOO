package Library.services;

import Library.resources.Book;
import Library.resources.Magazine;
import Library.resources.Resource;

/**
 * Factory class to create instances of {@link Resource} objects.
 * Supports creating {@link Book} and {@link Magazine} resources
 * based on a specified type.
 */
public class FactoryResource {

    /**
     * Creates a {@link Resource} instance based on the specified type.
     * Supports creation of {@link Book} and {@link Magazine}.
     *
     * @param type   the resource type (e.g., "book", "magazine")
     * @param name   the resource name or title
     * @param author the resource author
     * @param id     the unique identifier of the resource
     * @return a new {@link Resource} instance matching the given type
     */
    public static Resource createResource(String type, String name, String author, String id) {
        return switch (type.toLowerCase()) {
            case "book" -> new Book(id, name, author);
            case "magazine" -> new Magazine(name, id, author);
            default -> throw new IllegalArgumentException("Type not supported");
        };
    }
}
