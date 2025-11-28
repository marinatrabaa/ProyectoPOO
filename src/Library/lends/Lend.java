package Library.lends;

import java.time.LocalDate;

import Library.Users.User;
import Library.resources.Resource;

public class Lend {
    
    private Resource resource;
    private User user;
    private LocalDate startDate;
    private LocalDate finishDate;
    private boolean returned = false;

    public Lend(Resource r, User u){
        this.resource = r;
        this.user = u;
        this.startDate = LocalDate.now();
        this.finishDate = startDate.plusDays(15); //por defecto 15 days
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

    public boolean isFinished(){
        return !returned && LocalDate.now().isAfter(finishDate);
    }

    public void checkReturned(){
        this.returned = true;
        resource.returnResource();;
    }

    @Override
    public String toString() {
        return "Prestamo de " + resource.getName() + 
               " a " + user.getName() +
               " | Inicio: " + startDate + 
               " | Vence: " + finishDate + 
               " | Devuelto: " + returned;
    }

}
