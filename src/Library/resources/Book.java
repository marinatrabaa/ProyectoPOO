package Library.resources;

public class Book extends Resource{
    
    private String author;
    private int numberPages;

    public Book(String id, String name, String author, int numPages){
        super(name, id, author);
        this.numberPages = numPages;
    }

    @Override
    public void showDetails() {
        System.out.printf("Author name: " + this.author + ", name: " + name, ", number of pages: " + this.numberPages);
    }
}
