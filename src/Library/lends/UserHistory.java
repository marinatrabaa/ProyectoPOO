package Library.lends;

import java.util.LinkedList;


public class UserHistory{

    protected int loans;

    private LinkedList<Lend> activeLends;
    private LinkedList<Lend> finishedLends;

    public UserHistory(){
        activeLends = new LinkedList<>();
        finishedLends = new LinkedList<>();
    }


    public void addLend(Lend l){
        activeLends.add(l);
    }

    public void deleteLend(Lend l){
        if(activeLends.remove(l)){
            finishedLends.add(l);
        }
    }

    public LinkedList<Lend> getActiveLendList(){
        return activeLends;
    }

    public LinkedList<Lend> getFinishedLendList(){
        return finishedLends;
    }

    public int getActiveLoans(){
        return loans;
    }

    public void showHistory() {
        System.out.println("---- User History ----");
        System.out.println("Active:");
        activeLends.forEach(p -> System.out.println("  - " + p));
        System.out.println("Ended:");
        finishedLends.forEach(p -> System.out.println("  - " + p));
    }

}