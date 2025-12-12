package Library.resources;

/**
 * Represents a book in the library system.
 * Inherits from {@link Resource} and provides specific details
 * for books.
 */
public class Book extends Resource {

    /**
     * Creates a new book with the specified ID, name, and author.
     *
     * @param id     the unique identifier of the book
     * @param name   the title of the book
     * @param author the author of the book
     */
    public Book(String id, String name, String author) {
        super(name, id, author);
    }

    /**
     * Displays details of the book, including its type, author, and title.
     */
    @Override
    public void showDetails() {
        System.out.printf("Resource type: Book, author name: " + this.author + ", name: " + name);
    }
}
