package Library.resources;

public class Book extends Resource{

    public Book(String id, String name, String author){
        super(name, id, author);
    }

    @Override
    public void showDetails() {
        System.out.printf("Resource type: Book, author name: " + this.author + ", name: " + name);
    }
}
