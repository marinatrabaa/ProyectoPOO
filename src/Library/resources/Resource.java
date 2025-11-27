package Library.resources;

public abstract class Resource {
    
    protected String name;
    protected String id;
    protected boolean free = true;
    protected String author;

    public Resource(String name, String id, String author){
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public abstract void showDetails();

    public boolean isFree(){
        return free;
    }

    public void lendResource(){
        this.free = false;

    }

    public void returnResource(){
        this.free = true;
    }

    public String getName(){
        return name;
    }
    
}
