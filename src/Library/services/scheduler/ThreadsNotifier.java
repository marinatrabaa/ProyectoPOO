package Library.services.scheduler;

import java.util.LinkedList;

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
        while(true){
            try{
                sleep(5000);

                for(User u: manager.getUsers()){
                    if (u instanceof Reader reader) {
                    LinkedList<Lend> finished = reader.getHistory().getFinishedLendList();

                    for (Lend l : finished) {
                        notifier.sendEmail(l.getFinishDate(), reader.getName());
                    }
                    }

                }
            }catch (InterruptedException e){
                break;
            }
    
        }
    }
}