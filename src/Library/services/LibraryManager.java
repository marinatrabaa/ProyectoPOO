package Library.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Library.Users.User;
import Library.resources.Resource;
import Library.services.strategy.SearchStrategy;

public class LibraryManager {
    
    private static LibraryManager instance;

    private Map<String, Resource> cataloge;
    private List<User> users;

    private LibraryManager(){
        cataloge = new HashMap<>();
        users = new ArrayList<>();
    }

    public static LibraryManager getInstance(){
        if(instance == null){
            instance = new LibraryManager();
        }
        return instance;
    }

    public void addResource(Resource r) {
        cataloge.put(r.getId(), r);
    }

    public void removeRsource(String id) {
        cataloge.remove(id);
    }

    public List<User> getUsers(){
        return users;
    }

    public void addUser(User u){
        users.add(u);
    }

    public Map<String, Resource> getCataloge(){
        return cataloge;
    }

     public LinkedList<Resource> search(SearchStrategy strategy, String query) {
        return strategy.search(cataloge.values(), query);
    }

}
