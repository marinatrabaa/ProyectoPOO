package Library.resources;

public class Magazine extends Resource {

    public Magazine(String name, String id, String author){
        super(name, id, author);
    }

    @Override
    public void showDetails() {
        System.out.println("Resource type: Magazine, author name: " + author);
    }
    
}
