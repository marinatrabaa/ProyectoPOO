package Library.resources;

/**
 * Represents a magazine in the library system.
 * Inherits from {@link Resource} and provides specific details
 * for magazines.
 */
public class Magazine extends Resource {

    /**
     * Creates a new magazine with the specified name, ID, and author.
     *
     * @param name   the title of the magazine
     * @param id     the unique identifier of the magazine
     * @param author the author or editor of the magazine
     */
    public Magazine(String name, String id, String author) {
        super(name, id, author);
    }

    /**
     * Displays details of the magazine, including its type and author name.
     */
    @Override
    public void showDetails() {
        System.out.println("Resource type: Magazine, author name: " + author);
    }
}
