package Library.services.strategy;

import java.util.Collection;

import Library.collections.MyLinkedList;
import Library.resources.Resource;

public interface SearchStrategy {
    
    MyLinkedList<Resource> search(Collection<Resource> resources, String query);

}
