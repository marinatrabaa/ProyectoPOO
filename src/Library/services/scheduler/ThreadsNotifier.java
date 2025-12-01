package Library.services.scheduler;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import Library.Users.Reader;
import Library.Users.User;
import Library.lends.Lend;
import Library.services.LibraryManager;
import Library.services.Notifier;

public class ThreadsNotifier extends Thread{

    private LibraryManager manager;
    private Notifier notifier;
    
    public ThreadsNotifier (LibraryManager m, Notifier n){
        this.manager = m;
        this.notifier = n;
    }

    public void run(){

        Set<Lend> notifiedLends = new HashSet<>();

        while(!Thread.currentThread().isInterrupted()){
            try{
                sleep(5000);
                System.out.println("EJECUTANDO RUN DEL HILO");

                for(User u: manager.getUsers()){
                    if (u instanceof Reader reader) {
                        for(Lend l : reader.getHistory().getFinishedLendList()){
                            if(!notifiedLends.contains(l)){
                                System.out.println("Hay prestamos finalizados. Name: " + l.getResource().getName() + 
                                                    " | Reader: " + reader.getName());
                                
                            notifier.sendEmail(l.getFinishDate(), reader.getName());
                            }
                            notifiedLends.add(l);
                        }
                    }
                }
            }catch (InterruptedException e){
                break;
            }
    
        }
    }
}