package Library.services.strategy;

import java.util.Collection;
import java.util.LinkedList;
import Library.resources.Resource;

/**
 * Strategy interface for searching resources in the library system.
 * Implementations define different search algorithms or criteria.
 */
public interface SearchStrategy {

    /**
     * Searches the given collection of resources based on the specified query.
     *
     * @param resources the collection of {@link Resource} objects to search
     * @param query     the search query string
     * @return a {@link LinkedList} of resources matching the query
     */
    LinkedList<Resource> search(Collection<Resource> resources, String query);
}
