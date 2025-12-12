package Library.services.strategy;

import java.util.Collection;
import java.util.LinkedList;
import Library.resources.Resource;

/**
 * Implementation of {@link SearchStrategy} that searches resources by their authors.
 * The search is case-insensitive and returns all resources whose author names contain
 * the given query string.
 */
public class AuthorSearch implements SearchStrategy {

    /**
     * Searches the given collection of resources and returns those whose author names
     * contain the specified query, ignoring case.
     *
     * @param resources the collection of {@link Resource} objects to search
     * @param query     the text to look for within each resource's author name
     * @return a {@link LinkedList} of resources whose author names contain the query
     */
    @Override
    public LinkedList<Resource> search(Collection<Resource> resources, String query) {
        String q = query.toLowerCase();
        return new LinkedList<Resource>(
                resources.stream()
                         .filter(r -> r.getAuthor().toLowerCase().contains(q))
                         .toList()
        );
    }
}
