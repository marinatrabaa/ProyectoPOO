package Library.lends;

import java.util.ArrayList;
import java.util.List;

import Library.collections.MyLinkedList;

public class UserHistory{


    //sin tocar solo esta puesto para que no pete el usuario lector
    protected int loans;

    private MyLinkedList<Lend> activeLends = new MyLinkedList<>();
    private MyLinkedList<Lend> finishedLends = new MyLinkedList<>();

    public void addLend(Lend l){
        activeLends.add(l);
    }

    public void deleteLend(Lend l){
        if(activeLends.remove(l)){
            finishedLends.add(l);
        }
    }

    public int getNumberActiveLends(){
        return activeLends.size();
    }

    public MyLinkedList<Lend> getActiveLendList(){
        return activeLends;
    }

    public MyLinkedList<Lend> getFinishedLendList(){
        return finishedLends;
    }

    public int getActiveLoans(){
        return loans;
    }

    public void showHistory() {
        System.out.println("---- User History ----");
        System.out.println("Active:");
        activeLends.forEach(p -> System.out.println("  - " + p));
        System.out.println("Finalizados:");
        finishedLends.forEach(p -> System.out.println("  - " + p));
    }

}