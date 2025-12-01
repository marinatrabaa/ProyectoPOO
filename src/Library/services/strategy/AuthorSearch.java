package Library.services.strategy;

import java.util.Collection;
import java.util.LinkedList;

import Library.resources.Resource;

public class AuthorSearch implements SearchStrategy {

    @Override
    public LinkedList<Resource> search(Collection<Resource> resources, String query) {
        return new LinkedList<Resource> (resources.stream().filter(r -> r.getAuthor().contains(query.toLowerCase())).toList());
    }
    
}
