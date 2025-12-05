package Library.lends;

import java.time.LocalDate;

import Library.Users.User;
import Library.resources.Resource;

public class Lend {
    
    private int id;
    private Resource resource;
    private User user;
    private LocalDate startDate;
    private LocalDate finishDate;
    private boolean returned = false;

    public Lend(Resource r, User u){
        this.resource = r;
        this.user = u;
        this.startDate = LocalDate.now();
        this.finishDate = startDate.plusDays(15); //by default 15 days
    }

    public Lend(Resource r, User u, LocalDate start, LocalDate end){
        this.resource = r;
        this.user = u;
        this.startDate = start;
        this.finishDate = end;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Resource getResource(){
        return resource;
    }

    public User getUser(){
        return user;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getFinishDate(){
        return finishDate;
    }

    public boolean isReturned(){
        return returned;
    }

    public void checkReturned(){
        this.returned = true;
        resource.returnResource();;
    }

    @Override
    public String toString() {
        return "Loan of " + resource.getName() + 
               " to " + user.getName() +
               " | Start: " + startDate + 
               " | Due: " + finishDate + 
               " | Returned: " + returned;
    }

    public void setReturned(boolean b) {
        returned = b;
    }
}