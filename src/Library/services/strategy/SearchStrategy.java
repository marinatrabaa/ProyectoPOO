package Library.services.strategy;

import java.util.Collection;
import java.util.LinkedList;

import Library.resources.Resource;

public interface SearchStrategy {
    
    LinkedList<Resource> search(Collection<Resource> resources, String query);

}
