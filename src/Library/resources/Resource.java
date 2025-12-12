package Library.resources;

/**
 * Abstract class representing a library resource.
 * A resource has a name, an ID, an author, and a status indicating
 * whether it is currently available (free) or lent.
 */
public abstract class Resource {

    protected String name;
    protected String id;
    protected boolean free = true;
    protected String author;

    /**
     * Creates a new resource with the specified name, ID, and author.
     *
     * @param name   the name or title of the resource
     * @param id     the unique identifier of the resource
     * @param author the author or creator of the resource
     */
    public Resource(String name, String id, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    /**
     * Displays detailed information about the resource.
     * Must be implemented by subclasses.
     */
    public abstract void showDetails();

    /**
     * Checks if the resource is currently available for lending.
     *
     * @return {@code true} if the resource is free, {@code false} if it is lent
     */
    public boolean isFree() {
        return free;
    }

    /**
     * Marks the resource as lent, making it unavailable.
     */
    public void lendResource() {
        this.free = false;
    }

    /**
     * Marks the resource as returned, making it available again.
     */
    public void returnResource() {
        this.free = true;
    }

    /**
     * Returns the name of the resource.
     *
     * @return the resource name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the unique identifier of the resource.
     *
     * @return the resource ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the author of the resource.
     *
     * @return the resource author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns a string representation of the resource, including
     * name, author, and ID.
     *
     * @return a descriptive string of the resource
     */
    @Override
    public String toString() {
        return getName() + " by " + getAuthor() + " (id: " + getId() + ")";
    }
}
