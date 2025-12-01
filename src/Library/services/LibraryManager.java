package Library.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import Library.Users.User;
import Library.resources.Resource;
import Library.services.strategy.SearchStrategy;

public class LibraryManager {
    
    private static LibraryManager instance;

    private Map<String, Resource> cataloge;
    private Map<String, User> users;

    private LibraryManager(){
        cataloge = new HashMap<>();
        users = new HashMap<>();
    }

    public static LibraryManager getInstance(){
        if(instance == null){
            return new LibraryManager();
        }
        return instance;
    }

    public void addResource(Resource r) {
        cataloge.put(r.getId(), r);
    }

    public void removeRsource(String id) {
        cataloge.remove(id);
    }

    public Collection<User> getUsers(){
        return users.values();
    }

    public Map<String, Resource> getCataloge(){
        return cataloge;
    }

     public LinkedList<Resource> search(SearchStrategy strategy, String query) {
        return strategy.search(cataloge.values(), query);
    }

}
