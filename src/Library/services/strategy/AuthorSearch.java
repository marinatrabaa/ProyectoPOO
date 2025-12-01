package Library.services.strategy;

import java.util.Collection;
import java.util.LinkedList;

import Library.resources.Resource;

public class AuthorSearch implements SearchStrategy {

    @Override
    public LinkedList<Resource> search(Collection<Resource> resources, String query) {
        String q = query.toLowerCase();
        return new LinkedList<Resource> (resources.stream().filter(r -> r.getAuthor().toLowerCase().contains(q)).toList());
    }
    
}
