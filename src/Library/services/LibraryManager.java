package Library.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Library.Users.User;
import Library.resources.Resource;
import Library.services.strategy.SearchStrategy;

/**
 * Singleton class that manages the library system.
 * Maintains the catalog of resources and the list of users,
 * and provides methods to search, add, and remove resources and users.
 */
public class LibraryManager {

    private static LibraryManager instance;

    private Map<String, Resource> cataloge;
    private List<User> users;

    private LibraryManager() {
        cataloge = new HashMap<>();
        users = new ArrayList<>();
    }

    /**
     * Returns the single instance of {@code LibraryManager}.
     * If no instance exists, it creates one.
     *
     * @return the unique {@code LibraryManager} instance
     */
    public static LibraryManager getInstance() {
        if (instance == null) {
            instance = new LibraryManager();
        }
        return instance;
    }

    /**
     * Adds a new resource to the catalog.
     *
     * @param r the {@link Resource} to add
     */
    public void addResource(Resource r) {
        cataloge.put(r.getId(), r);
    }

    /**
     * Removes a resource from the catalog by its ID.
     *
     * @param id the ID of the resource to remove
     */
    public void removeRsource(String id) {
        cataloge.remove(id);
    }

    /**
     * Returns the list of users in the library system.
     *
     * @return a {@link List} of {@link User} objects
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Adds a new user to the library system.
     *
     * @param u the {@link User} to add
     */
    public void addUser(User u) {
        users.add(u);
    }

    /**
     * Returns the catalog of resources.
     *
     * @return a {@link Map} of resource ID to {@link Resource} objects
     */
    public Map<String, Resource> getCataloge() {
        return cataloge;
    }

    /**
     * Searches the resource catalog using the provided search strategy.
     *
     * @param strategy the {@link SearchStrategy} to apply
     * @param query    the search query
     * @return a {@link LinkedList} of matching {@link Resource} objects
     */
    public LinkedList<Resource> search(SearchStrategy strategy, String query) {
        return strategy.search(cataloge.values(), query);
    }
}
